package com.example.governance.records;

// Raised by the service when a client tries to create the same external record twice.
public class DuplicateRecordException extends RuntimeException {

	public DuplicateRecordException(String externalId) {
		// The message is reused by ApiExceptionHandler when it builds the 409 JSON response.
		super("Record already exists: " + externalId);
	}
}
