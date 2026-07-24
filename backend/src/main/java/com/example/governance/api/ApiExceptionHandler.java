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

// Central place that translates Java exceptions into professional API error responses.
// IMPORTANT: Controllers should not duplicate this error-response logic.
@RestControllerAdvice
public class ApiExceptionHandler {

	// Domain rule failure: retention policy names are unique.
	@ExceptionHandler(DuplicateRetentionPolicyException.class)
	public ResponseEntity<ApiErrorResponse> handleDuplicateRetentionPolicy(
			DuplicateRetentionPolicyException exception,
			HttpServletRequest request
	) {
		// Build one consistent JSON body for the client.
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
		// 409 means the request conflicts with current server state.
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
		// 404 means the client referenced a resource ID that the system cannot find.
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
		// Collect validation errors from annotations such as @NotBlank and @Size.
		List<FieldValidationError> fieldErrors = exception.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(fieldError -> new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
				// IMPORTANT: Sort errors so tests and clients see a stable order.
				.sorted(Comparator.comparing(FieldValidationError::field))
				.toList();

		// Return the same error shape as other API failures, plus field-level details.
		ApiErrorResponse response = ApiErrorResponse.validation(
				"Request validation failed",
				request.getRequestURI(),
				fieldErrors
		);

		return ResponseEntity.badRequest().body(response);
	}
}
