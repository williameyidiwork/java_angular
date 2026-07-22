package com.example.governance.retention;

public class DuplicateRetentionPolicyException extends RuntimeException {

	public DuplicateRetentionPolicyException(String name) {
		super("Retention policy already exists: " + name);
	}
}
