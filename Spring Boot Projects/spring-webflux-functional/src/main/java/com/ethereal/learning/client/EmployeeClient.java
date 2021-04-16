package com.ethereal.learning.client;

import org.springframework.web.reactive.function.client.WebClient;

import com.ethereal.learning.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class EmployeeClient {

	WebClient client = WebClient.create("http://localhost:8080");

	public void consume() {

		Mono<Employee> employeeMono = client.get().uri("/employees/{id}", "1").retrieve().bodyToMono(Employee.class);
		employeeMono.subscribe(System.out::println);
		Flux<Employee> employeeFlux = client.get().uri("/employees").retrieve().bodyToFlux(Employee.class);
		employeeFlux.subscribe(System.out::println);
	}

}
