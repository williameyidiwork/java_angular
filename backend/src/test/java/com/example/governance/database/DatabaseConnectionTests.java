package com.example.governance.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DatabaseConnectionTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void connectsToConfiguredPostgresDatabase() {
		Map<String, Object> result = jdbcTemplate.queryForMap(
				"select current_database() as database_name, current_user as user_name"
		);

		assertEquals("governance", result.get("database_name"));
		assertEquals("governance", result.get("user_name"));
	}
}
