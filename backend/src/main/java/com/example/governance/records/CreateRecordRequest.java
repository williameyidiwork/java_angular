package com.example.governance.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

// JSON body used when a client creates a record.
// IMPORTANT: This DTO is separate from the entity so API input does not directly control database fields.
public record CreateRecordRequest(
		// External ID is the business identifier from another system, like a CRM or file import.
		// @NotBlank rejects null, empty, and whitespace-only strings.
		@NotBlank
		// @Size keeps the API limit aligned with the database column length.
		@Size(max = 120)
		String externalId,

		// Name is the human-readable label users see in API results and later UI screens.
		// @NotBlank makes the field required in the request JSON.
		@NotBlank
		// The database column allows up to 255 characters, so the API enforces the same rule.
		@Size(max = 255)
		String name,

		// Optional: a record can be created before a retention policy is assigned.
		// If present, the service must verify that this ID exists.
		UUID retentionPolicyId
) {
}
