package com.example.governance.retention;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RetentionPolicyRepositoryTests {

	@Autowired
	private RetentionPolicyRepository repository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@BeforeEach
	@AfterEach
	void cleanTables() {
		jdbcTemplate.update("delete from records");
		jdbcTemplate.update("delete from retention_policies");
	}

	@Test
	void savesAndFindsRetentionPolicyByName() {
		RetentionPolicy saved = repository.saveAndFlush(
				new RetentionPolicy("Financial Records", "Keep finance records for seven years.", 2555)
		);

		Optional<RetentionPolicy> found = repository.findByName("Financial Records");

		assertTrue(found.isPresent());
		assertNotNull(saved.getId());
		assertEquals(saved.getId(), found.get().getId());
		assertEquals("Financial Records", found.get().getName());
		assertEquals(2555, found.get().getRetentionPeriodDays());
	}

	@Test
	void rejectsDuplicateRetentionPolicyName() {
		repository.saveAndFlush(new RetentionPolicy("Legal Hold", "Required by legal team.", 3650));

		assertThrows(DataIntegrityViolationException.class, () ->
				repository.saveAndFlush(new RetentionPolicy("Legal Hold", "Duplicate name.", 30))
		);
	}
}
