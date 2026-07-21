package com.example.governance.api;

public record ApplicationInfoResponse(
		String name,
		String description,
		String apiVersion,
		String status
) {
}
