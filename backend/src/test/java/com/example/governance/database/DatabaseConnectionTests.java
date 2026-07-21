package com.example.governance.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DatabaseConnectionTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void connectsToConfiguredPostgresDatabase() {
		Map<String, Object> result = jdbcTemplate.queryForMap(
				"select current_database() as database_name, current_user as user_name"
		);

		assertThat(result)
				.containsEntry("database_name", "governance")
				.containsEntry("user_name", "governance");
	}
}
