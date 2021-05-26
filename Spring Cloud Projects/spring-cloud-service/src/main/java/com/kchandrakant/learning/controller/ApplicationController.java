package com.kchandrakant.learning.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kchandrakant.learning.domain.Car;

import reactor.core.publisher.Flux;

@RestController
class ApplicationController {

	@GetMapping("/cars")
	public Flux<Car> getCars() {

		return Flux.fromIterable(Arrays.asList(new Car(UUID.randomUUID(), "MyCar", LocalDate.now())));
	}
}
