package com.example.governance.records;

import com.example.governance.retention.RetentionPolicy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;
import java.util.UUID;

// Maps the Flyway-created records table to a Java object.
@Entity
@Table(name = "records")
public class GovernanceRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(nullable = false, updatable = false)
	private UUID id;

	@NotBlank
	@Size(max = 120)
	@Column(name = "external_id", nullable = false, length = 120)
	private String externalId;

	@NotBlank
	@Size(max = 255)
	@Column(nullable = false)
	private String name;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 40)
	private RecordStatus status;

	// A record may have a retention policy, but the database allows it to be empty.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "retention_policy_id")
	private RetentionPolicy retentionPolicy;

	@Column(name = "created_at", nullable = false, insertable = false, updatable = false)
	private OffsetDateTime createdAt;

	@Column(name = "updated_at", nullable = false, insertable = false)
	private OffsetDateTime updatedAt;

	@Column(name = "expires_at")
	private OffsetDateTime expiresAt;

	@Column(name = "archived_at")
	private OffsetDateTime archivedAt;

	@Column(name = "purged_at")
	private OffsetDateTime purgedAt;

	protected GovernanceRecord() {
	}

	public GovernanceRecord(String externalId, String name, RecordStatus status, RetentionPolicy retentionPolicy) {
		this.externalId = externalId;
		this.name = name;
		this.status = status;
		this.retentionPolicy = retentionPolicy;
	}

	public UUID getId() {
		return id;
	}

	public String getExternalId() {
		return externalId;
	}

	public String getName() {
		return name;
	}

	public RecordStatus getStatus() {
		return status;
	}

	public RetentionPolicy getRetentionPolicy() {
		return retentionPolicy;
	}

	public UUID getRetentionPolicyId() {
		return retentionPolicy == null ? null : retentionPolicy.getId();
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}

	public OffsetDateTime getExpiresAt() {
		return expiresAt;
	}

	public OffsetDateTime getArchivedAt() {
		return archivedAt;
	}

	public OffsetDateTime getPurgedAt() {
		return purgedAt;
	}
}
