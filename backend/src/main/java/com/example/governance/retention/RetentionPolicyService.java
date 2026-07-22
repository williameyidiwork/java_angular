package com.example.governance.retention;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetentionPolicyService {

	private final RetentionPolicyRepository repository;

	public RetentionPolicyService(RetentionPolicyRepository repository) {
		this.repository = repository;
	}

	public RetentionPolicy createPolicy(String name, String description, Integer retentionPeriodDays) {
		repository.findByName(name)
				.ifPresent(existing -> {
					throw new DuplicateRetentionPolicyException(name);
				});

		return repository.save(new RetentionPolicy(name, description, retentionPeriodDays));
	}

	public List<RetentionPolicy> listPolicies() {
		return repository.findAll(Sort.by("name").ascending());
	}
}
