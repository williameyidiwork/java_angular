package com.example.governance.retention;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RetentionPolicyController.class)
class RetentionPolicyControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private RetentionPolicyService service;

	@Test
	void createPolicyReturnsCreatedPolicy() throws Exception {
		when(service.createPolicy("Financial Records", "Keep finance records for seven years.", 2555))
				.thenReturn(new RetentionPolicy("Financial Records", "Keep finance records for seven years.", 2555));

		mockMvc.perform(post("/api/v1/retention-policies")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "name": "Financial Records",
								  "description": "Keep finance records for seven years.",
								  "retentionPeriodDays": 2555
								}
								"""))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name").value("Financial Records"))
				.andExpect(jsonPath("$.description").value("Keep finance records for seven years."))
				.andExpect(jsonPath("$.retentionPeriodDays").value(2555));

		verify(service).createPolicy("Financial Records", "Keep finance records for seven years.", 2555);
	}

	@Test
	void listPoliciesReturnsPoliciesFromService() throws Exception {
		when(service.listPolicies()).thenReturn(List.of(
				new RetentionPolicy("Finance", "Finance retention.", 2555),
				new RetentionPolicy("Legal", "Legal retention.", 3650)
		));

		mockMvc.perform(get("/api/v1/retention-policies"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Finance"))
				.andExpect(jsonPath("$[0].retentionPeriodDays").value(2555))
				.andExpect(jsonPath("$[1].name").value("Legal"))
				.andExpect(jsonPath("$[1].retentionPeriodDays").value(3650));

		verify(service).listPolicies();
	}

	@Test
	void createPolicyRejectsInvalidRequest() throws Exception {
		mockMvc.perform(post("/api/v1/retention-policies")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "name": "",
								  "description": "Invalid request.",
								  "retentionPeriodDays": 0
								}
								"""))
				.andExpect(status().isBadRequest());
	}
}
