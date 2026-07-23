package com.example.governance.records;

import java.time.OffsetDateTime;
import java.util.UUID;

// JSON body returned to API clients for a record.
public record GovernanceRecordResponse(
		UUID id,
		String externalId,
		String name,
		RecordStatus status,
		UUID retentionPolicyId,
		OffsetDateTime createdAt,
		OffsetDateTime updatedAt,
		OffsetDateTime expiresAt,
		OffsetDateTime archivedAt,
		OffsetDateTime purgedAt
) {

	// Converts the database entity into the JSON shape returned by the API.
	public static GovernanceRecordResponse from(GovernanceRecord record) {
		return new GovernanceRecordResponse(
				record.getId(),
				record.getExternalId(),
				record.getName(),
				record.getStatus(),
				record.getRetentionPolicyId(),
				record.getCreatedAt(),
				record.getUpdatedAt(),
				record.getExpiresAt(),
				record.getArchivedAt(),
				record.getPurgedAt()
		);
	}
}
