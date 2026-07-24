package com.example.governance.records;

import java.time.OffsetDateTime;
import java.util.UUID;

// JSON body returned to API clients for a record.
// IMPORTANT: This DTO controls output; clients do not receive the JPA entity directly.
public record GovernanceRecordResponse(
		// Database-generated identifier.
		UUID id,
		// Business identifier from the client or source system.
		String externalId,
		// Human-readable record title.
		String name,
		// Current lifecycle state.
		RecordStatus status,
		// Only expose the policy ID so the JSON stays simple.
		UUID retentionPolicyId,
		// Audit-style lifecycle timestamps owned by the database or future workflow steps.
		OffsetDateTime createdAt,
		OffsetDateTime updatedAt,
		OffsetDateTime expiresAt,
		OffsetDateTime archivedAt,
		OffsetDateTime purgedAt
) {

	// Converts the database entity into the JSON shape returned by the API.
	public static GovernanceRecordResponse from(GovernanceRecord record) {
		// This method is the boundary between persistence model and API model.
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
