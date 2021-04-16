package com.ethereal.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ethereal.learning.persistence.Persister;

import reactor.core.publisher.Mono;

@RestController
public class MyController {

	@Autowired
	private Persister persister;

	@GetMapping("persist/db")
	public Mono<String> listDbEntries() {
		return Mono.just(persister.listDbEntries());
	}

	@GetMapping("persist/process/{order}")
	public void process(@PathVariable int order) {
		persister.change(order, "PROCESS");
	}

	@GetMapping("persist/send/{order}")
	public void send(@PathVariable int order) {
		persister.change(order, "SEND");
	}

	@GetMapping("persist/deliver/{order}")
	public void deliver(@PathVariable int order) {
		persister.change(order, "DELIVER");
	}

}
