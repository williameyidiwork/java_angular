package com.example.governance.records;

import com.example.governance.retention.RetentionPolicy;
import com.example.governance.retention.RetentionPolicyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Integration test: real Spring Boot app plus real PostgreSQL.
@SpringBootTest
class GovernanceRecordRepositoryIT {

	@Autowired
	private GovernanceRecordRepository repository;

	@Autowired
	private RetentionPolicyRepository retentionPolicyRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@BeforeEach
	@AfterEach
	void cleanTables() {
		// Delete child table first because records can reference retention_policies.
		jdbcTemplate.update("delete from records");
		jdbcTemplate.update("delete from retention_policies");
	}

	@Test
	void savesAndFindsRecordByExternalId() {
		// saveAndFlush writes immediately, so the next query proves PostgreSQL can read it back.
		GovernanceRecord saved = repository.saveAndFlush(
				new GovernanceRecord("REC-100", "Quarterly Finance Report", RecordStatus.ACTIVE, null)
		);

		Optional<GovernanceRecord> found = repository.findByExternalId("REC-100");

		assertTrue(found.isPresent());
		assertNotNull(saved.getId());
		assertEquals(saved.getId(), found.get().getId());
		assertEquals("Quarterly Finance Report", found.get().getName());
		assertEquals(RecordStatus.ACTIVE, found.get().getStatus());
	}

	@Test
	void savesRecordWithRetentionPolicyReference() {
		// First create the parent row, then create a record that points to it.
		RetentionPolicy policy = retentionPolicyRepository.saveAndFlush(
				new RetentionPolicy("Finance Seven Years", "Keep finance records for seven years.", 2555)
		);
		repository.saveAndFlush(new GovernanceRecord("REC-200", "Invoice Batch", RecordStatus.ACTIVE, policy));

		UUID storedPolicyId = jdbcTemplate.queryForObject(
				"select retention_policy_id from records where external_id = ?",
				UUID.class,
				"REC-200"
		);

		assertEquals(policy.getId(), storedPolicyId);
	}

	@Test
	void rejectsDuplicateExternalId() {
		// The database unique constraint protects us even if application code misses a duplicate.
		repository.saveAndFlush(new GovernanceRecord("REC-300", "Original Record", RecordStatus.ACTIVE, null));

		assertThrows(DataIntegrityViolationException.class, () ->
				repository.saveAndFlush(new GovernanceRecord("REC-300", "Duplicate Record", RecordStatus.ACTIVE, null))
		);
	}
}
