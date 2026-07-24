package com.example.governance.records;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

// Spring Data creates the database implementation for this interface.
// IMPORTANT: We declare what we need; Spring generates the runtime implementation.
public interface GovernanceRecordRepository extends JpaRepository<GovernanceRecord, UUID> {

	// Spring Data reads this method name and creates a query using the externalId field.
	// Optional is used because the record may or may not exist.
	Optional<GovernanceRecord> findByExternalId(String externalId);

	// Spring Data creates a paginated query using the status field.
	Page<GovernanceRecord> findByStatus(RecordStatus status, Pageable pageable);

	// Spring Data creates a case-insensitive search on externalId using a SQL LIKE pattern.
	Page<GovernanceRecord> findByExternalIdContainingIgnoreCase(String externalId, Pageable pageable);

	// Spring Data combines both filters when the client sends status and externalId together.
	Page<GovernanceRecord> findByStatusAndExternalIdContainingIgnoreCase(
			RecordStatus status,
			String externalId,
			Pageable pageable
	);
}
