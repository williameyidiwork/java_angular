package com.example.governance.records;

import com.example.governance.retention.RetentionPolicy;
import com.example.governance.retention.RetentionPolicyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
@ExtendWith(MockitoExtension.class)
class GovernanceRecordServiceTests {

	@Mock
	private GovernanceRecordRepository repository;

	@Mock
	private RetentionPolicyRepository retentionPolicyRepository;

	@InjectMocks
	private GovernanceRecordService service;

	@Test
	void createsActiveRecordWhenExternalIdDoesNotExist() {
		when(repository.findByExternalId("REC-100")).thenReturn(Optional.empty());
		when(repository.save(any(GovernanceRecord.class))).thenAnswer(invocation -> invocation.getArgument(0));

		GovernanceRecord created = service.createRecord("REC-100", "Quarterly Finance Report", null);

		assertEquals("REC-100", created.getExternalId());
		assertEquals("Quarterly Finance Report", created.getName());
		assertEquals(RecordStatus.ACTIVE, created.getStatus());
		assertNull(created.getRetentionPolicy());
		verify(repository).findByExternalId("REC-100");
		verify(repository).save(any(GovernanceRecord.class));
		verify(retentionPolicyRepository, never()).findById(any(UUID.class));
	}

	@Test
	void createsRecordWithRetentionPolicyWhenPolicyExists() {
		UUID policyId = UUID.randomUUID();
		RetentionPolicy policy = new RetentionPolicy("Finance Seven Years", "Keep finance records.", 2555);
		when(repository.findByExternalId("REC-200")).thenReturn(Optional.empty());
		when(retentionPolicyRepository.findById(policyId)).thenReturn(Optional.of(policy));
		when(repository.save(any(GovernanceRecord.class))).thenAnswer(invocation -> invocation.getArgument(0));

		GovernanceRecord created = service.createRecord("REC-200", "Invoice Batch", policyId);

		assertEquals(policy, created.getRetentionPolicy());
		verify(repository).findByExternalId("REC-200");
		verify(retentionPolicyRepository).findById(policyId);
		verify(repository).save(any(GovernanceRecord.class));
	}

	@Test
	void rejectsDuplicateExternalIdBeforeSaving() {
		when(repository.findByExternalId("REC-300"))
				.thenReturn(Optional.of(new GovernanceRecord("REC-300", "Existing Record", RecordStatus.ACTIVE, null)));

		DuplicateRecordException exception = assertThrows(
				DuplicateRecordException.class,
				() -> service.createRecord("REC-300", "Duplicate Record", null)
		);

		assertEquals("Record already exists: REC-300", exception.getMessage());
		verify(repository).findByExternalId("REC-300");
		verify(repository, never()).save(any(GovernanceRecord.class));
		verify(retentionPolicyRepository, never()).findById(any(UUID.class));
	}

	@Test
	void rejectsMissingRetentionPolicyBeforeSaving() {
		UUID missingPolicyId = UUID.randomUUID();
		when(repository.findByExternalId("REC-400")).thenReturn(Optional.empty());
		when(retentionPolicyRepository.findById(missingPolicyId)).thenReturn(Optional.empty());

		RetentionPolicyNotFoundException exception = assertThrows(
				RetentionPolicyNotFoundException.class,
				() -> service.createRecord("REC-400", "Unknown Policy Record", missingPolicyId)
		);

		assertEquals("Retention policy not found: " + missingPolicyId, exception.getMessage());
		verify(repository).findByExternalId("REC-400");
		verify(retentionPolicyRepository).findById(missingPolicyId);
		verify(repository, never()).save(any(GovernanceRecord.class));
	}

	@Test
	void listsRecordsByExternalId() {
		List<GovernanceRecord> records = List.of(
				new GovernanceRecord("REC-100", "Finance", RecordStatus.ACTIVE, null),
				new GovernanceRecord("REC-200", "Legal", RecordStatus.ACTIVE, null)
		);
		Sort sortByExternalId = Sort.by("externalId").ascending();
		when(repository.findAll(sortByExternalId)).thenReturn(records);

		List<GovernanceRecord> result = service.listRecords();

		assertEquals(records, result);
		verify(repository).findAll(sortByExternalId);
	}
}
