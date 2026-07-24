package com.example.governance.records;

// These values must match the database check constraint in the Flyway migration.
// IMPORTANT: If a value is added here, the database constraint must be updated too.
public enum RecordStatus {

	// Normal state for a newly created record.
	ACTIVE,
	// Future state: record is no longer active but still retained.
	ARCHIVED,
	// Future state: record has passed retention and has been removed.
	PURGED,
	// Future state: lifecycle processing tried and failed.
	FAILED
}
