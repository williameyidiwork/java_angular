package com.example.governance.records;

import com.example.governance.api.PageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	// Example: GET /api/v1/records?page=0&size=20
	@GetMapping
	public PageResponse<GovernanceRecordResponse> listRecords(
			// page is zero-based: page=0 means the first page.
			@RequestParam(defaultValue = "0") int page,
			// size controls the maximum number of rows returned in one response.
			@RequestParam(defaultValue = "20") int size
	) {
		// Ask the service for one page, then convert each entity into response JSON.
		Page<GovernanceRecordResponse> records = service.listRecords(page, size)
				.map(GovernanceRecordResponse::from);

		// Wrap the page metadata in a stable API response shape.
		return PageResponse.from(records);
	}
}
