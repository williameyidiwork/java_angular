package com.example.governance.records;

// These values must match the database check constraint in the Flyway migration.
public enum RecordStatus {

	ACTIVE,
	ARCHIVED,
	PURGED,
	FAILED
}
