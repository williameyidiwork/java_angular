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
@RestController
@RequestMapping("/api/v1/records")
public class GovernanceRecordController {

	private final GovernanceRecordService service;

	public GovernanceRecordController(GovernanceRecordService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GovernanceRecordResponse createRecord(@Valid @RequestBody CreateRecordRequest request) {
		GovernanceRecord record = service.createRecord(
				request.externalId(),
				request.name(),
				request.retentionPolicyId()
		);

		return GovernanceRecordResponse.from(record);
	}

	@GetMapping
	public List<GovernanceRecordResponse> listRecords() {
		return service.listRecords()
				.stream()
				.map(GovernanceRecordResponse::from)
				.toList();
	}
}
