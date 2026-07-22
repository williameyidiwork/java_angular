package com.example.governance.retention;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateRetentionPolicyRequest(
		@NotBlank
		@Size(max = 120)
		String name,

		@Size(max = 500)
		String description,

		@NotNull
		@Positive
		Integer retentionPeriodDays
) {
}
