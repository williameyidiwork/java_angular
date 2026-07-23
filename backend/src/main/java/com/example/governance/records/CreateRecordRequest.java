package com.example.governance.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

// JSON body used when a client creates a record.
public record CreateRecordRequest(
		// External ID is the business identifier from another system, like a CRM or file import.
		@NotBlank
		@Size(max = 120)
		String externalId,

		// Name is the human-readable label users see in API results and later UI screens.
		@NotBlank
		@Size(max = 255)
		String name,

		// Optional: a record can be created before a retention policy is assigned.
		UUID retentionPolicyId
) {
}
