package com.example.governance.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationInfoControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getApplicationInfoReturnsBackendMetadata() throws Exception {
		mockMvc.perform(get("/api/v1/info"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("governance-platform"))
				.andExpect(jsonPath("$.description").value("Enterprise data governance backend"))
				.andExpect(jsonPath("$.apiVersion").value("v1"))
				.andExpect(jsonPath("$.status").value("UP"));
	}

	@Test
	void getApplicationInfoReturnsStatusOk() throws Exception {
		mockMvc.perform(get("/api/v1/info"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("governance-platform"));
	}

	@Test
	void getApplicationInfoReturnsDescription() throws Exception {
		mockMvc.perform(get("/api/v1/info"))
				.andExpect(jsonPath("$.description").value("Enterprise data governance backend"));
	}
}
