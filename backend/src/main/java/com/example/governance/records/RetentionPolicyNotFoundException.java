package com.example.governance.records;

import java.util.UUID;

// Raised by the service when the client points a record to a missing retention policy.
public class RetentionPolicyNotFoundException extends RuntimeException {

	public RetentionPolicyNotFoundException(UUID retentionPolicyId) {
		// The message is reused by ApiExceptionHandler when it builds the 404 JSON response.
		super("Retention policy not found: " + retentionPolicyId);
	}
}
