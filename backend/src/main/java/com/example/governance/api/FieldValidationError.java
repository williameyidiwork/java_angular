package com.example.governance.api;

public record FieldValidationError(
		String field,
		String message
) {
}
