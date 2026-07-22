package com.example.governance.retention;

import java.time.OffsetDateTime;
import java.util.UUID;

public record RetentionPolicyResponse(
		UUID id,
		String name,
		String description,
		Integer retentionPeriodDays,
		OffsetDateTime createdAt,
		OffsetDateTime updatedAt
) {

	public static RetentionPolicyResponse from(RetentionPolicy policy) {
		return new RetentionPolicyResponse(
				policy.getId(),
				policy.getName(),
				policy.getDescription(),
				policy.getRetentionPeriodDays(),
				policy.getCreatedAt(),
				policy.getUpdatedAt()
		);
	}
}
