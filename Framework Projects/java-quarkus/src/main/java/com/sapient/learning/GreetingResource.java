package com.sapient.learning;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

	@Inject
	GreetingService service;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "hello\n";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/greeting/{name}")
	public String greeting(@PathParam("name") String name) {
		return service.greeting(name);
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/async")
	public CompletionStage<String> helloAsync() {
		return CompletableFuture.supplyAsync(() -> {
			return "hello async\n";
		});
	}
}