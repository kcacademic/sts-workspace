package com.kchandrakant.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
class ApplicationController {

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@GetMapping(value = "/serve")
	public String zipkinService1() {

		return (String) restTemplate.exchange("http://localhost:8082/serve", HttpMethod.GET, null,
				new ParameterizedTypeReference<String>() {
				}).getBody();
	}
}
