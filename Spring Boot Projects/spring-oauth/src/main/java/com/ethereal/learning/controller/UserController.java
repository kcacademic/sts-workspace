package com.ethereal.learning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ethereal.learning.data.UserProfile;

@RestController
public class UserController {

	@RequestMapping("/api/users/me")
	public ResponseEntity<UserProfile> profile() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName() + "@sapient.com";

		UserProfile profile = new UserProfile();
		profile.setName(auth.getName());
		profile.setAuthorities(auth.getAuthorities().toString());
		profile.setEmail(email);

		return ResponseEntity.ok(profile);
	}

}