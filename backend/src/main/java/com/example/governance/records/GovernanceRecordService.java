package com.example.governance.records;

import com.example.governance.retention.RetentionPolicy;
import com.example.governance.retention.RetentionPolicyRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

// Service layer: owns business rules for records.
// IMPORTANT: Controllers call this class; repositories should not contain business decisions.
@Service
public class GovernanceRecordService {

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

	public List<GovernanceRecord> listRecords() {
		// Stable ordering makes API responses easier to test and easier for clients to read.
		return repository.findAll(Sort.by("externalId").ascending());
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
}
