package com.example.governance.records;

// Raised when the client asks for a page number or page size outside our supported range.
public class InvalidRecordPageRequestException extends RuntimeException {

	public InvalidRecordPageRequestException(String message) {
		// ApiExceptionHandler reuses this message when it builds the 400 JSON response.
		super(message);
	}
}
