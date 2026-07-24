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
// IMPORTANT: This test proves JPA mappings work with the real schema, not with mocks.
@SpringBootTest
class GovernanceRecordRepositoryIT {

	// Real Spring Data repository connected to PostgreSQL.
	@Autowired
	private GovernanceRecordRepository repository;

	// Real retention repository used to create parent retention_policy rows.
	@Autowired
	private RetentionPolicyRepository retentionPolicyRepository;

	// JdbcTemplate lets the test run small SQL checks directly against PostgreSQL.
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@BeforeEach
	@AfterEach
	void cleanTables() {
		// Run before and after each test so one test cannot leak data into another test.
		// Delete child table first because records can reference retention_policies.
		jdbcTemplate.update("delete from records");
		jdbcTemplate.update("delete from retention_policies");
	}

	@Test
	void savesAndFindsRecordByExternalId() {
		// saveAndFlush writes immediately, so the next query proves PostgreSQL can read it back.
		// Arrange and act: save one record through JPA.
		GovernanceRecord saved = repository.saveAndFlush(
				new GovernanceRecord("REC-100", "Quarterly Finance Report", RecordStatus.ACTIVE, null)
		);

		// Act: use the custom Spring Data query method.
		Optional<GovernanceRecord> found = repository.findByExternalId("REC-100");

		// Assert: the row was saved, assigned an ID, and read back correctly.
		assertTrue(found.isPresent());
		assertNotNull(saved.getId());
		assertEquals(saved.getId(), found.get().getId());
		assertEquals("Quarterly Finance Report", found.get().getName());
		assertEquals(RecordStatus.ACTIVE, found.get().getStatus());
	}

	@Test
	void savesRecordWithRetentionPolicyReference() {
		// First create the parent row, then create a record that points to it.
		// Arrange: create the retention policy that the record will reference.
		RetentionPolicy policy = retentionPolicyRepository.saveAndFlush(
				new RetentionPolicy("Finance Seven Years", "Keep finance records for seven years.", 2555)
		);
		// Act: save a record with the parent policy object.
		repository.saveAndFlush(new GovernanceRecord("REC-200", "Invoice Batch", RecordStatus.ACTIVE, policy));

		// Assert with SQL: verify the foreign-key column contains the expected policy ID.
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
		// Arrange: save the first record.
		repository.saveAndFlush(new GovernanceRecord("REC-300", "Original Record", RecordStatus.ACTIVE, null));

		// Act and assert: saving another row with the same external_id fails.
		assertThrows(DataIntegrityViolationException.class, () ->
				repository.saveAndFlush(new GovernanceRecord("REC-300", "Duplicate Record", RecordStatus.ACTIVE, null))
		);
	}
}
