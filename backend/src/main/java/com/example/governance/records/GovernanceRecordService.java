package com.example.governance.records;

import com.example.governance.retention.RetentionPolicy;
import com.example.governance.retention.RetentionPolicyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

// Service layer: owns business rules for records.
// IMPORTANT: Controllers call this class; repositories should not contain business decisions.
@Service
public class GovernanceRecordService {

	private static final int MAX_PAGE_SIZE = 100;

	// Repository for the records table.
	private final GovernanceRecordRepository repository;
	// Repository for checking whether a requested retention policy exists.
	private final RetentionPolicyRepository retentionPolicyRepository;

	// Constructor injection makes dependencies explicit and easy to replace in tests.
	public GovernanceRecordService(
			GovernanceRecordRepository repository,
			RetentionPolicyRepository retentionPolicyRepository
	) {
		this.repository = repository;
		this.retentionPolicyRepository = retentionPolicyRepository;
	}

	public GovernanceRecord createRecord(String externalId, String name, UUID retentionPolicyId) {
		// Business rule: external IDs are identifiers from outside systems, so they must be unique.
		repository.findByExternalId(externalId)
				.ifPresent(existing -> {
					// Stop before save so the API can return a clear 409 Conflict.
					throw new DuplicateRecordException(externalId);
				});

		// If the request included a policy ID, load it before creating the record.
		RetentionPolicy retentionPolicy = findRetentionPolicy(retentionPolicyId);

		// New records always start ACTIVE; later phases will change status during archive/purge.
		return repository.save(new GovernanceRecord(externalId, name, RecordStatus.ACTIVE, retentionPolicy));
	}

	public Page<GovernanceRecord> listRecords(int page, int size, RecordStatus status, String externalId) {
		validatePageRequest(page, size);

		// Stable ordering makes API responses easier to test and easier for clients to read.
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("externalId").ascending());
		String externalIdSearch = normalizeExternalIdSearch(externalId);

		if (status != null && externalIdSearch != null) {
			// Combined filters: useful when clients want one status within one external ID pattern.
			return repository.findByStatusAndExternalIdContainingIgnoreCase(status, externalIdSearch, pageRequest);
		}

		if (status != null) {
			// Optional filter: ask the database for only records matching this status.
			return repository.findByStatus(status, pageRequest);
		}

		if (externalIdSearch != null) {
			// Optional search: match any external ID containing the provided text, ignoring case.
			return repository.findByExternalIdContainingIgnoreCase(externalIdSearch, pageRequest);
		}

		// IMPORTANT: findAll(PageRequest) lets the database return only one page instead of every row.
		return repository.findAll(pageRequest);
	}

	private String normalizeExternalIdSearch(String externalId) {
		// Treat null, empty, and whitespace-only search text as "no externalId filter".
		if (externalId == null || externalId.isBlank()) {
			return null;
		}

		// Trim spaces so externalId= REC-100  behaves like externalId=REC-100.
		return externalId.trim();
	}

	private RetentionPolicy findRetentionPolicy(UUID retentionPolicyId) {
		// No policy ID means the record is allowed to be created without a policy.
		if (retentionPolicyId == null) {
			return null;
		}

		// IMPORTANT: Do not save a record that points to a retention policy that does not exist.
		return retentionPolicyRepository.findById(retentionPolicyId)
				.orElseThrow(() -> new RetentionPolicyNotFoundException(retentionPolicyId));
	}

	private void validatePageRequest(int page, int size) {
		if (page < 0) {
			throw new InvalidRecordPageRequestException("Page index must be zero or greater.");
		}

		if (size < 1 || size > MAX_PAGE_SIZE) {
			throw new InvalidRecordPageRequestException("Page size must be between 1 and " + MAX_PAGE_SIZE + ".");
		}
	}
}
