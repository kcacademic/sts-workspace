package com.sapient.learning;

import ratpack.server.RatpackServer;

import com.sapient.learning.handler.UserHandler;

public class Application {
	public static void main(String[] args) throws Exception {
		new Application();
	}

	private Application() throws Exception {
		final RatpackServer server = RatpackServer.of(s -> s
				.handlers(chain -> chain
						.get(ctx -> ctx.render("Hello World!"))
						.get("users", new UserHandler())));
		server.start();
	}

}