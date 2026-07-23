package com.example.governance.records;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

// Spring Data creates the database implementation for this interface.
public interface GovernanceRecordRepository extends JpaRepository<GovernanceRecord, UUID> {

	// Spring Data reads this method name and creates a query using the externalId field.
	Optional<GovernanceRecord> findByExternalId(String externalId);
}
