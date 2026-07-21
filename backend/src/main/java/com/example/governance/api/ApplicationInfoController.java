package com.example.governance.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/info")
public class ApplicationInfoController {

	@GetMapping
	public ApplicationInfoResponse getApplicationInfo() {
		return new ApplicationInfoResponse(
				"governance-platform",
				"Enterprise data governance backend",
				"v1",
				"UP"
		);
	}
}
