package com.example.governance.records;

import com.example.governance.api.ApiExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Web test: real controller plus fake service.
@WebMvcTest(GovernanceRecordController.class)
@Import(ApiExceptionHandler.class)
class GovernanceRecordControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private GovernanceRecordService service;

	@Test
	void createRecordReturnsCreatedRecord() throws Exception {
		// Arrange: the controller gets a fake service result so this test stays focused on HTTP behavior.
		when(service.createRecord("REC-100", "Quarterly Finance Report", null))
				.thenReturn(new GovernanceRecord("REC-100", "Quarterly Finance Report", RecordStatus.ACTIVE, null));

		mockMvc.perform(post("/api/v1/records")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "externalId": "REC-100",
								  "name": "Quarterly Finance Report"
								}
								"""))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.externalId").value("REC-100"))
				.andExpect(jsonPath("$.name").value("Quarterly Finance Report"))
				.andExpect(jsonPath("$.status").value("ACTIVE"));

		verify(service).createRecord("REC-100", "Quarterly Finance Report", null);
	}

	@Test
	void listRecordsReturnsRecordsFromService() throws Exception {
		when(service.listRecords()).thenReturn(List.of(
				new GovernanceRecord("REC-100", "Finance Report", RecordStatus.ACTIVE, null),
				new GovernanceRecord("REC-200", "Legal Contract", RecordStatus.ACTIVE, null)
		));

		mockMvc.perform(get("/api/v1/records"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].externalId").value("REC-100"))
				.andExpect(jsonPath("$[0].status").value("ACTIVE"))
				.andExpect(jsonPath("$[1].externalId").value("REC-200"))
				.andExpect(jsonPath("$[1].name").value("Legal Contract"));

		verify(service).listRecords();
	}

	@Test
	void createRecordRejectsInvalidRequest() throws Exception {
		// Empty strings violate @NotBlank on CreateRecordRequest.
		mockMvc.perform(post("/api/v1/records")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "externalId": "",
								  "name": ""
								}
								"""))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.error").value("Bad Request"))
				.andExpect(jsonPath("$.message").value("Request validation failed"))
				.andExpect(jsonPath("$.path").value("/api/v1/records"))
				.andExpect(jsonPath("$.fieldErrors[0].field").value("externalId"))
				.andExpect(jsonPath("$.fieldErrors[1].field").value("name"));
	}

	@Test
	void createRecordReturnsConflictForDuplicateExternalId() throws Exception {
		// Arrange: the fake service throws the same exception the real service would throw.
		when(service.createRecord("REC-300", "Duplicate Record", null))
				.thenThrow(new DuplicateRecordException("REC-300"));

		mockMvc.perform(post("/api/v1/records")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "externalId": "REC-300",
								  "name": "Duplicate Record"
								}
								"""))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.status").value(409))
				.andExpect(jsonPath("$.error").value("Conflict"))
				.andExpect(jsonPath("$.message").value("Record already exists: REC-300"))
				.andExpect(jsonPath("$.path").value("/api/v1/records"));

		verify(service).createRecord("REC-300", "Duplicate Record", null);
	}

	@Test
	void createRecordReturnsNotFoundForMissingRetentionPolicy() throws Exception {
		UUID missingPolicyId = UUID.fromString("11111111-1111-1111-1111-111111111111");
		// Arrange: the fake service says the requested retention policy does not exist.
		when(service.createRecord("REC-400", "Unknown Policy Record", missingPolicyId))
				.thenThrow(new RetentionPolicyNotFoundException(missingPolicyId));

		mockMvc.perform(post("/api/v1/records")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "externalId": "REC-400",
								  "name": "Unknown Policy Record",
								  "retentionPolicyId": "11111111-1111-1111-1111-111111111111"
								}
								"""))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.error").value("Not Found"))
				.andExpect(jsonPath("$.message").value("Retention policy not found: " + missingPolicyId))
				.andExpect(jsonPath("$.path").value("/api/v1/records"));

		verify(service).createRecord("REC-400", "Unknown Policy Record", missingPolicyId);
	}
}
