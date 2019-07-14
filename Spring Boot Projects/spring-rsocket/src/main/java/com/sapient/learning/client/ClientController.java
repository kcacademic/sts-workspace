package com.sapient.learning.client;

import org.reactivestreams.Publisher;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

	private final RSocketRequester rSocketRequester;

	public ClientController(RSocketRequester rSocketRequester) {
		this.rSocketRequester = rSocketRequester;
	}

	@GetMapping(value = "/hello/{name}")
	public Publisher<String> current(@PathVariable("name") String name) {
		return rSocketRequester.route("greeting").data(name).retrieveMono(String.class);
	}
}