package com.example.governance.records;

import com.example.governance.retention.RetentionPolicy;
import com.example.governance.retention.RetentionPolicyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Unit test: real service plus fake repositories.
// IMPORTANT: This file tests business rules without needing PostgreSQL.
@ExtendWith(MockitoExtension.class)
class GovernanceRecordServiceTests {

	// Fake records repository controlled by each test.
	@Mock
	private GovernanceRecordRepository repository;

	// Fake retention policy repository controlled by each test.
	@Mock
	private RetentionPolicyRepository retentionPolicyRepository;

	// Mockito builds the real service and injects the fake repositories above.
	@InjectMocks
	private GovernanceRecordService service;

	@Test
	void createsActiveRecordWhenExternalIdDoesNotExist() {
		// Arrange: external ID does not exist, and save returns the entity it receives.
		when(repository.findByExternalId("REC-100")).thenReturn(Optional.empty());
		when(repository.save(any(GovernanceRecord.class))).thenAnswer(invocation -> invocation.getArgument(0));

		// Act: call the real service method.
		GovernanceRecord created = service.createRecord("REC-100", "Quarterly Finance Report", null);

		// Assert: service created an ACTIVE record without a retention policy.
		assertEquals("REC-100", created.getExternalId());
		assertEquals("Quarterly Finance Report", created.getName());
		assertEquals(RecordStatus.ACTIVE, created.getStatus());
		assertNull(created.getRetentionPolicy());
		// Assert: service checked for duplicates and saved the record.
		verify(repository).findByExternalId("REC-100");
		verify(repository).save(any(GovernanceRecord.class));
		// Important: no policy ID means the service should not query the policy repository.
		verify(retentionPolicyRepository, never()).findById(any(UUID.class));
	}

	@Test
	void createsRecordWithRetentionPolicyWhenPolicyExists() {
		// Arrange: create a fake policy and make the policy repository return it.
		UUID policyId = UUID.randomUUID();
		RetentionPolicy policy = new RetentionPolicy("Finance Seven Years", "Keep finance records.", 2555);
		when(repository.findByExternalId("REC-200")).thenReturn(Optional.empty());
		when(retentionPolicyRepository.findById(policyId)).thenReturn(Optional.of(policy));
		when(repository.save(any(GovernanceRecord.class))).thenAnswer(invocation -> invocation.getArgument(0));

		// Act: create a record that references the policy ID.
		GovernanceRecord created = service.createRecord("REC-200", "Invoice Batch", policyId);

		// Assert: service attached the policy and saved the record.
		assertEquals(policy, created.getRetentionPolicy());
		verify(repository).findByExternalId("REC-200");
		verify(retentionPolicyRepository).findById(policyId);
		verify(repository).save(any(GovernanceRecord.class));
	}

	@Test
	void rejectsDuplicateExternalIdBeforeSaving() {
		// Arrange: the repository says a record with REC-300 already exists.
		when(repository.findByExternalId("REC-300"))
				.thenReturn(Optional.of(new GovernanceRecord("REC-300", "Existing Record", RecordStatus.ACTIVE, null)));

		// Act and assert: duplicate external ID becomes a domain exception.
		DuplicateRecordException exception = assertThrows(
				DuplicateRecordException.class,
				() -> service.createRecord("REC-300", "Duplicate Record", null)
		);

		// Assert: service stopped before saving or checking unrelated policy data.
		assertEquals("Record already exists: REC-300", exception.getMessage());
		verify(repository).findByExternalId("REC-300");
		verify(repository, never()).save(any(GovernanceRecord.class));
		verify(retentionPolicyRepository, never()).findById(any(UUID.class));
	}

	@Test
	void rejectsMissingRetentionPolicyBeforeSaving() {
		// Arrange: external ID is free, but the requested policy ID does not exist.
		UUID missingPolicyId = UUID.randomUUID();
		when(repository.findByExternalId("REC-400")).thenReturn(Optional.empty());
		when(retentionPolicyRepository.findById(missingPolicyId)).thenReturn(Optional.empty());

		// Act and assert: missing policy becomes a domain exception.
		RetentionPolicyNotFoundException exception = assertThrows(
				RetentionPolicyNotFoundException.class,
				() -> service.createRecord("REC-400", "Unknown Policy Record", missingPolicyId)
		);

		// Assert: service checked the policy and refused to save invalid data.
		assertEquals("Retention policy not found: " + missingPolicyId, exception.getMessage());
		verify(repository).findByExternalId("REC-400");
		verify(retentionPolicyRepository).findById(missingPolicyId);
		verify(repository, never()).save(any(GovernanceRecord.class));
	}

	@Test
	void listsPagedRecordsWithoutFilters() {
		// Arrange: expected records and expected page request.
		List<GovernanceRecord> records = List.of(
				new GovernanceRecord("REC-100", "Finance", RecordStatus.ACTIVE, null),
				new GovernanceRecord("REC-200", "Legal", RecordStatus.ACTIVE, null)
		);
		PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("externalId").ascending());
		when(repository.findAll(pageRequest)).thenReturn(new PageImpl<>(records, pageRequest, 5));

		// Act: call the real service list method.
		Page<GovernanceRecord> result = service.listRecords(0, 2, null, null);

		// Assert: result came from repository using the expected stable sort.
		assertEquals(records, result.getContent());
		assertEquals(0, result.getNumber());
		assertEquals(2, result.getSize());
		assertEquals(5, result.getTotalElements());
		verify(repository).findAll(pageRequest);
	}

	@Test
	void listsPagedRecordsByStatus() {
		// Arrange: expected archived records and expected page request.
		List<GovernanceRecord> records = List.of(
				new GovernanceRecord("REC-300", "Archived Contract", RecordStatus.ARCHIVED, null)
		);
		PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("externalId").ascending());
		when(repository.findByStatus(RecordStatus.ARCHIVED, pageRequest))
				.thenReturn(new PageImpl<>(records, pageRequest, 1));

		// Act: call the real service list method with a status filter.
		Page<GovernanceRecord> result = service.listRecords(0, 20, RecordStatus.ARCHIVED, null);

		// Assert: service used the filtered repository method instead of unfiltered findAll.
		assertEquals(records, result.getContent());
		assertEquals(1, result.getTotalElements());
		verify(repository).findByStatus(RecordStatus.ARCHIVED, pageRequest);
		verify(repository, never()).findAll(any(PageRequest.class));
	}

	@Test
	void searchesPagedRecordsByExternalId() {
		// Arrange: expected records and expected page request.
		List<GovernanceRecord> records = List.of(
				new GovernanceRecord("REC-API-001", "API Search Record", RecordStatus.ACTIVE, null)
		);
		PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("externalId").ascending());
		when(repository.findByExternalIdContainingIgnoreCase("rec-api", pageRequest))
				.thenReturn(new PageImpl<>(records, pageRequest, 1));

		// Act: call the real service list method with externalId search text.
		Page<GovernanceRecord> result = service.listRecords(0, 20, null, "rec-api");

		// Assert: service used the search repository method instead of unfiltered findAll.
		assertEquals(records, result.getContent());
		assertEquals(1, result.getTotalElements());
		verify(repository).findByExternalIdContainingIgnoreCase("rec-api", pageRequest);
		verify(repository, never()).findAll(any(PageRequest.class));
	}

	@Test
	void trimsExternalIdSearchBeforeQuerying() {
		// Arrange: the client accidentally included spaces around the search text.
		List<GovernanceRecord> records = List.of(
				new GovernanceRecord("REC-TRIM-001", "Trimmed Search Record", RecordStatus.ACTIVE, null)
		);
		PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("externalId").ascending());
		when(repository.findByExternalIdContainingIgnoreCase("REC-TRIM", pageRequest))
				.thenReturn(new PageImpl<>(records, pageRequest, 1));

		// Act: service normalizes the search text before asking the repository.
		Page<GovernanceRecord> result = service.listRecords(0, 20, null, "  REC-TRIM  ");

		// Assert: repository receives trimmed search text.
		assertEquals(records, result.getContent());
		verify(repository).findByExternalIdContainingIgnoreCase("REC-TRIM", pageRequest);
	}

	@Test
	void treatsBlankExternalIdSearchAsNoFilter() {
		// Arrange: blank search text should behave like the client did not send externalId.
		List<GovernanceRecord> records = List.of(
				new GovernanceRecord("REC-100", "Finance", RecordStatus.ACTIVE, null)
		);
		PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("externalId").ascending());
		when(repository.findAll(pageRequest)).thenReturn(new PageImpl<>(records, pageRequest, 1));

		// Act: whitespace-only search text is ignored.
		Page<GovernanceRecord> result = service.listRecords(0, 20, null, "   ");

		// Assert: service used the normal paginated query.
		assertEquals(records, result.getContent());
		verify(repository).findAll(pageRequest);
		verify(repository, never()).findByExternalIdContainingIgnoreCase(any(String.class), any(PageRequest.class));
	}

	@Test
	void listsPagedRecordsByStatusAndExternalId() {
		// Arrange: expected records when both status and externalId filters are present.
		List<GovernanceRecord> records = List.of(
				new GovernanceRecord("REC-CONTRACT-002", "Archived Contract", RecordStatus.ARCHIVED, null)
		);
		PageRequest pageRequest = PageRequest.of(0, 20, Sort.by("externalId").ascending());
		when(repository.findByStatusAndExternalIdContainingIgnoreCase(RecordStatus.ARCHIVED, "contract", pageRequest))
				.thenReturn(new PageImpl<>(records, pageRequest, 1));

		// Act: call the real service with both filters.
		Page<GovernanceRecord> result = service.listRecords(0, 20, RecordStatus.ARCHIVED, "contract");

		// Assert: service used the combined query so both filters apply in the database.
		assertEquals(records, result.getContent());
		assertEquals(1, result.getTotalElements());
		verify(repository).findByStatusAndExternalIdContainingIgnoreCase(RecordStatus.ARCHIVED, "contract", pageRequest);
		verify(repository, never()).findByStatus(any(RecordStatus.class), any(PageRequest.class));
		verify(repository, never()).findByExternalIdContainingIgnoreCase(any(String.class), any(PageRequest.class));
		verify(repository, never()).findAll(any(PageRequest.class));
	}

	@Test
	void rejectsNegativePageNumber() {
		// Act and assert: page numbers are zero-based, so negative numbers are invalid.
		InvalidRecordPageRequestException exception = assertThrows(
				InvalidRecordPageRequestException.class,
				() -> service.listRecords(-1, 20, null, null)
		);

		assertEquals("Page index must be zero or greater.", exception.getMessage());
		verify(repository, never()).findAll(any(PageRequest.class));
	}

	@Test
	void rejectsPageSizeBelowOne() {
		// Act and assert: size must be at least 1 because a page with 0 rows is not useful.
		InvalidRecordPageRequestException exception = assertThrows(
				InvalidRecordPageRequestException.class,
				() -> service.listRecords(0, 0, null, null)
		);

		assertEquals("Page size must be between 1 and 100.", exception.getMessage());
		verify(repository, never()).findAll(any(PageRequest.class));
	}

	@Test
	void rejectsPageSizeAboveMaximum() {
		// Act and assert: cap page size so one request cannot ask for too many rows.
		InvalidRecordPageRequestException exception = assertThrows(
				InvalidRecordPageRequestException.class,
				() -> service.listRecords(0, 101, null, null)
		);

		assertEquals("Page size must be between 1 and 100.", exception.getMessage());
		verify(repository, never()).findAll(any(PageRequest.class));
	}
}
