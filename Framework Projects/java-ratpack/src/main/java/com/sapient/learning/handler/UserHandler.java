package com.sapient.learning.handler;

import static ratpack.jackson.Jackson.json;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import ratpack.handling.Context;
import ratpack.handling.Handler;

public class UserHandler implements Handler {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(UserHandler.class.getSimpleName());

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void handle(Context context) throws Exception {
		context.parse(Map.class).then(payload -> {
			Map<String, Object> parameters = (Map<String, Object>) payload.get("parameters");
			Map<String, Object> result = new LinkedHashMap<>();

			result.put("name", "Kumar Chandrakant");

			context.render(json(result));
		});
	}

}