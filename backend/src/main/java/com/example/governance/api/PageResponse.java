package com.example.governance.api;

import org.springframework.data.domain.Page;

import java.util.List;

// Reusable JSON wrapper for paginated API responses.
// IMPORTANT: We return our own DTO instead of exposing Spring's Page implementation directly.
public record PageResponse<T>(
		// The items on the current page.
		List<T> content,
		// Zero-based page number requested by the client.
		int page,
		// Maximum number of items requested for this page.
		int size,
		// Total number of matching rows across all pages.
		long totalElements,
		// Total number of pages available for the current query.
		int totalPages,
		// True when this is the first page.
		boolean first,
		// True when this is the last page.
		boolean last
) {

	// Converts Spring Data's Page object into the simple response shape our API owns.
	public static <T> PageResponse<T> from(Page<T> page) {
		return new PageResponse<>(
				page.getContent(),
				page.getNumber(),
				page.getSize(),
				page.getTotalElements(),
				page.getTotalPages(),
				page.isFirst(),
				page.isLast()
		);
	}
}
