package com.example.governance.api;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ApiErrorResponse(
		int status,
		String error,
		String message,
		String path,
		List<FieldValidationError> fieldErrors
) {

	public static ApiErrorResponse of(HttpStatus status, String message, String path) {
		return new ApiErrorResponse(status.value(), status.getReasonPhrase(), message, path, List.of());
	}

	public static ApiErrorResponse validation(String message, String path, List<FieldValidationError> fieldErrors) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ApiErrorResponse(status.value(), status.getReasonPhrase(), message, path, fieldErrors);
	}
}
