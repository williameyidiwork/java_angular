package com.example.governance.records;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// REST controller: turns HTTP requests into service calls.
// IMPORTANT: This class should stay thin; business rules belong in GovernanceRecordService.
@RestController
// Base URL for every method in this controller.
@RequestMapping("/api/v1/records")
public class GovernanceRecordController {

	private final GovernanceRecordService service;

	// Constructor injection lets Spring provide the service dependency.
	public GovernanceRecordController(GovernanceRecordService service) {
		this.service = service;
	}

	// Handles POST /api/v1/records.
	@PostMapping
	// A successful create returns HTTP 201 Created instead of the default 200 OK.
	@ResponseStatus(HttpStatus.CREATED)
	public GovernanceRecordResponse createRecord(@Valid @RequestBody CreateRecordRequest request) {
		// @Valid checks CreateRecordRequest annotations before the service is called.
		// @RequestBody tells Spring to read JSON from the HTTP request body.
		GovernanceRecord record = service.createRecord(
				request.externalId(),
				request.name(),
				request.retentionPolicyId()
		);

		// Convert the entity to a response DTO before returning JSON to the client.
		return GovernanceRecordResponse.from(record);
	}

	// Handles GET /api/v1/records.
	@GetMapping
	public List<GovernanceRecordResponse> listRecords() {
		// Convert every entity from the service into the API response shape.
		return service.listRecords()
				.stream()
				.map(GovernanceRecordResponse::from)
				.toList();
	}
}
