package com.example.governance.retention;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetentionPolicyServiceTests {

	@Mock
	private RetentionPolicyRepository repository;

	@InjectMocks
	private RetentionPolicyService service;

	@Test
	void createsPolicyWhenNameDoesNotExist() {
		when(repository.findByName("Financial Records")).thenReturn(Optional.empty());
		when(repository.save(any(RetentionPolicy.class))).thenAnswer(invocation -> invocation.getArgument(0));

		RetentionPolicy created = service.createPolicy(
				"Financial Records",
				"Keep finance records for seven years.",
				2555
		);

		assertEquals("Financial Records", created.getName());
		assertEquals("Keep finance records for seven years.", created.getDescription());
		assertEquals(2555, created.getRetentionPeriodDays());
		verify(repository).findByName("Financial Records");
		verify(repository).save(any(RetentionPolicy.class));
	}

	@Test
	void rejectsDuplicatePolicyNameBeforeSaving() {
		when(repository.findByName("Legal Hold"))
				.thenReturn(Optional.of(new RetentionPolicy("Legal Hold", "Required by legal team.", 3650)));

		DuplicateRetentionPolicyException exception = assertThrows(
				DuplicateRetentionPolicyException.class,
				() -> service.createPolicy("Legal Hold", "Duplicate name.", 30)
		);

		assertEquals("Retention policy already exists: Legal Hold", exception.getMessage());
		verify(repository).findByName("Legal Hold");
		verify(repository, never()).save(any(RetentionPolicy.class));
	}

	@Test
	void listsPoliciesByName() {
		List<RetentionPolicy> policies = List.of(
				new RetentionPolicy("Finance", "Finance retention.", 2555),
				new RetentionPolicy("Legal", "Legal retention.", 3650)
		);
		Sort sortByName = Sort.by("name").ascending();
		when(repository.findAll(sortByName)).thenReturn(policies);

		List<RetentionPolicy> result = service.listPolicies();

		assertEquals(policies, result);
		verify(repository).findAll(sortByName);
	}
}
