package com.example.governance.api;

import com.example.governance.retention.DuplicateRetentionPolicyException;
import com.example.governance.records.DuplicateRecordException;
import com.example.governance.records.RetentionPolicyNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Comparator;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

	// Domain rule failure: retention policy names are unique.
	@ExceptionHandler(DuplicateRetentionPolicyException.class)
	public ResponseEntity<ApiErrorResponse> handleDuplicateRetentionPolicy(
			DuplicateRetentionPolicyException exception,
			HttpServletRequest request
	) {
		ApiErrorResponse response = ApiErrorResponse.of(
				HttpStatus.CONFLICT,
				exception.getMessage(),
				request.getRequestURI()
		);

		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}

	// Domain rule failure: record external IDs are unique.
	@ExceptionHandler(DuplicateRecordException.class)
	public ResponseEntity<ApiErrorResponse> handleDuplicateRecord(
			DuplicateRecordException exception,
			HttpServletRequest request
	) {
		ApiErrorResponse response = ApiErrorResponse.of(
				HttpStatus.CONFLICT,
				exception.getMessage(),
				request.getRequestURI()
		);

		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}

	// Lookup failure: the client sent a retention policy ID that does not exist.
	@ExceptionHandler(RetentionPolicyNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleMissingRetentionPolicy(
			RetentionPolicyNotFoundException exception,
			HttpServletRequest request
	) {
		ApiErrorResponse response = ApiErrorResponse.of(
				HttpStatus.NOT_FOUND,
				exception.getMessage(),
				request.getRequestURI()
		);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	// Bean Validation failure: request JSON did not satisfy annotations such as @NotBlank.
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleValidationError(
			MethodArgumentNotValidException exception,
			HttpServletRequest request
	) {
		List<FieldValidationError> fieldErrors = exception.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(fieldError -> new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
				.sorted(Comparator.comparing(FieldValidationError::field))
				.toList();

		ApiErrorResponse response = ApiErrorResponse.validation(
				"Request validation failed",
				request.getRequestURI(),
				fieldErrors
		);

		return ResponseEntity.badRequest().body(response);
	}
}
