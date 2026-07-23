package com.example.governance.records;

// Raised by the service when a client tries to create the same external record twice.
public class DuplicateRecordException extends RuntimeException {

	public DuplicateRecordException(String externalId) {
		super("Record already exists: " + externalId);
	}
}
