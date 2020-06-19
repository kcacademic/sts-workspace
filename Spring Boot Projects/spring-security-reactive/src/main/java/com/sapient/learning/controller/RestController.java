package com.sapient.learning.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;

import reactor.core.publisher.Mono;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@GetMapping("/who")
	Mono<String> who(@AuthenticationPrincipal UserDetails user) {
		return Mono.just("who: " + user.getUsername());
	}

	@GetMapping("/who-revised")
	Mono<String> whoRevised(@AuthenticationPrincipal UserDetails user) {
		if (User.class.isInstance(user))
			return Mono.just(user).ofType(User.class).map(u -> "who/custom: " + u.getUsername());

		return Mono.just("who/map-reactive: " + user.getUsername());
	}

}
