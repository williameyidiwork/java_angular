package com.example.governance.records;

import com.example.governance.retention.RetentionPolicy;
import com.example.governance.retention.RetentionPolicyRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GovernanceRecordService {

	private final GovernanceRecordRepository repository;
	private final RetentionPolicyRepository retentionPolicyRepository;

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
					throw new DuplicateRecordException(externalId);
				});

		RetentionPolicy retentionPolicy = findRetentionPolicy(retentionPolicyId);

		return repository.save(new GovernanceRecord(externalId, name, RecordStatus.ACTIVE, retentionPolicy));
	}

	public List<GovernanceRecord> listRecords() {
		// Stable ordering makes API responses easier to test and easier for clients to read.
		return repository.findAll(Sort.by("externalId").ascending());
	}

	private RetentionPolicy findRetentionPolicy(UUID retentionPolicyId) {
		if (retentionPolicyId == null) {
			return null;
		}

		return retentionPolicyRepository.findById(retentionPolicyId)
				.orElseThrow(() -> new RetentionPolicyNotFoundException(retentionPolicyId));
	}
}
