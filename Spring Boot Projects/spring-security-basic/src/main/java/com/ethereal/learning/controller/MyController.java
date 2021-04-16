package com.ethereal.learning.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@GetMapping("/api/user")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getUserGreeting() {
		System.out.println("Method getUserGreeting() invoked.");
		return "Hello User!";
	}

	@GetMapping("/api/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String getAdminGreeting() {
		System.out.println("Method getAdminGreeting() invoked.");
		return "Hello Admin!";
	}

}
