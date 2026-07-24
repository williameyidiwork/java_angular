package com.example.governance.records;

import com.example.governance.api.ApiExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
// IMPORTANT: This tests the HTTP layer without starting the full application server.
@WebMvcTest(GovernanceRecordController.class)
// Brings in the shared error handler so validation and service errors produce real API error JSON.
@Import(ApiExceptionHandler.class)
class GovernanceRecordControllerTests {

	// MockMvc lets the test perform fake HTTP requests against the controller.
	@Autowired
	private MockMvc mockMvc;

	// The service is mocked so this file only tests controller behavior.
	@MockitoBean
	private GovernanceRecordService service;

	@Test
	void createRecordReturnsCreatedRecord() throws Exception {
		// Arrange: the controller gets a fake service result so this test stays focused on HTTP behavior.
		when(service.createRecord("REC-100", "Quarterly Finance Report", null))
				.thenReturn(new GovernanceRecord("REC-100", "Quarterly Finance Report", RecordStatus.ACTIVE, null));

		// Act: send JSON to POST /api/v1/records.
		mockMvc.perform(post("/api/v1/records")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "externalId": "REC-100",
								  "name": "Quarterly Finance Report"
								}
								"""))
				// Assert: controller returns 201 and the expected response JSON.
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.externalId").value("REC-100"))
				.andExpect(jsonPath("$.name").value("Quarterly Finance Report"))
				.andExpect(jsonPath("$.status").value("ACTIVE"));

		// Assert: controller passed the correct values to the service.
		verify(service).createRecord("REC-100", "Quarterly Finance Report", null);
	}

	@Test
	void listRecordsReturnsFirstPageFromService() throws Exception {
		// Arrange: the fake service returns two records.
		PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("externalId").ascending());
		when(service.listRecords(0, 20)).thenReturn(new PageImpl<>(List.of(
				new GovernanceRecord("REC-100", "Finance Report", RecordStatus.ACTIVE, null),
				new GovernanceRecord("REC-200", "Legal Contract", RecordStatus.ACTIVE, null)
		), pageRequest, 2));

		// Act and assert: GET returns page metadata plus records in content.
		mockMvc.perform(get("/api/v1/records"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content[0].externalId").value("REC-100"))
				.andExpect(jsonPath("$.content[0].status").value("ACTIVE"))
				.andExpect(jsonPath("$.content[1].externalId").value("REC-200"))
				.andExpect(jsonPath("$.content[1].name").value("Legal Contract"))
				.andExpect(jsonPath("$.page").value(0))
				.andExpect(jsonPath("$.size").value(20))
				.andExpect(jsonPath("$.totalElements").value(2))
				.andExpect(jsonPath("$.totalPages").value(1))
				.andExpect(jsonPath("$.first").value(true))
				.andExpect(jsonPath("$.last").value(true));

		// Assert: controller asked the service for the list.
		verify(service).listRecords(0, 20);
	}

	@Test
	void listRecordsUsesRequestedPageAndSize() throws Exception {
		// Arrange: page=1 and size=1 means the client wants the second page with one item.
		PageRequest pageRequest = PageRequest.of(1, 1, Sort.by("externalId").ascending());
		when(service.listRecords(1, 1)).thenReturn(new PageImpl<>(List.of(
				new GovernanceRecord("REC-200", "Legal Contract", RecordStatus.ACTIVE, null)
		), pageRequest, 2));

		// Act and assert: query parameters are passed to the service and returned in the page metadata.
		mockMvc.perform(get("/api/v1/records")
						.param("page", "1")
						.param("size", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content[0].externalId").value("REC-200"))
				.andExpect(jsonPath("$.page").value(1))
				.andExpect(jsonPath("$.size").value(1))
				.andExpect(jsonPath("$.totalElements").value(2))
				.andExpect(jsonPath("$.totalPages").value(2))
				.andExpect(jsonPath("$.first").value(false))
				.andExpect(jsonPath("$.last").value(true));

		verify(service).listRecords(1, 1);
	}

	@Test
	void listRecordsReturnsBadRequestForInvalidPagination() throws Exception {
		// Arrange: the fake service applies the same pagination rule as the real service.
		when(service.listRecords(-1, 20))
				.thenThrow(new InvalidRecordPageRequestException("Page index must be zero or greater."));

		// Act and assert: invalid pagination becomes structured 400 JSON.
		mockMvc.perform(get("/api/v1/records")
						.param("page", "-1"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.error").value("Bad Request"))
				.andExpect(jsonPath("$.message").value("Page index must be zero or greater."))
				.andExpect(jsonPath("$.path").value("/api/v1/records"));

		verify(service).listRecords(-1, 20);
	}

	@Test
	void createRecordRejectsInvalidRequest() throws Exception {
		// Empty strings violate @NotBlank on CreateRecordRequest.
		// IMPORTANT: The service is not mocked here because validation should stop before service logic.
		mockMvc.perform(post("/api/v1/records")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								{
								  "externalId": "",
								  "name": ""
								}
								"""))
				// Assert: ApiExceptionHandler converts validation errors into structured 400 JSON.
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

		// Act and assert: duplicate records become HTTP 409 Conflict.
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

		// Assert: the controller sent the parsed JSON values into the service.
		verify(service).createRecord("REC-300", "Duplicate Record", null);
	}

	@Test
	void createRecordReturnsNotFoundForMissingRetentionPolicy() throws Exception {
		UUID missingPolicyId = UUID.fromString("11111111-1111-1111-1111-111111111111");
		// Arrange: the fake service says the requested retention policy does not exist.
		when(service.createRecord("REC-400", "Unknown Policy Record", missingPolicyId))
				.thenThrow(new RetentionPolicyNotFoundException(missingPolicyId));

		// Act and assert: a missing related resource becomes HTTP 404 Not Found.
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

		// Assert: the UUID string from JSON was converted to a UUID object.
		verify(service).createRecord("REC-400", "Unknown Policy Record", missingPolicyId);
	}
}
