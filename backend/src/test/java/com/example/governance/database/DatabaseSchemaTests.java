package com.example.governance.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DatabaseSchemaTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void flywayCreatesRecordsSchemaTables() {
		List<String> tableNames = jdbcTemplate.queryForList(
				"""
				select table_name
				from information_schema.tables
				where table_schema = 'public'
				order by table_name
				""",
				String.class
		);

		assertTrue(tableNames.contains("flyway_schema_history"));
		assertTrue(tableNames.contains("records"));
		assertTrue(tableNames.contains("retention_policies"));
	}

	@Test
	void recordsTableHasExpectedStatusConstraint() {
		Integer matchingConstraints = jdbcTemplate.queryForObject(
				"""
				select count(*)
				from information_schema.check_constraints
				where constraint_name = 'chk_records_status'
				""",
				Integer.class
		);

		assertEquals(1, matchingConstraints);
	}

	@Test
	void recordsTableReferencesRetentionPolicies() {
		Integer matchingForeignKeys = jdbcTemplate.queryForObject(
				"""
				select count(*)
				from information_schema.table_constraints
				where table_schema = 'public'
				  and table_name = 'records'
				  and constraint_name = 'fk_records_retention_policy'
				  and constraint_type = 'FOREIGN KEY'
				""",
				Integer.class
		);

		assertEquals(1, matchingForeignKeys);
	}
}
