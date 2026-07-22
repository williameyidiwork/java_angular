package com.example.governance.retention;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/retention-policies")
public class RetentionPolicyController {

	private final RetentionPolicyService service;

	public RetentionPolicyController(RetentionPolicyService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RetentionPolicyResponse createPolicy(@Valid @RequestBody CreateRetentionPolicyRequest request) {
		RetentionPolicy policy = service.createPolicy(
				request.name(),
				request.description(),
				request.retentionPeriodDays()
		);

		return RetentionPolicyResponse.from(policy);
	}

	@GetMapping
	public List<RetentionPolicyResponse> listPolicies() {
		return service.listPolicies()
				.stream()
				.map(RetentionPolicyResponse::from)
				.toList();
	}
}
