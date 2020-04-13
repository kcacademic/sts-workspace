package com.sapient.learning.my.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.restCall;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;

import akka.NotUsed;

public interface MyService extends Service {

	ServiceCall<NotUsed, String> my(String id);

	@Override
	default Descriptor descriptor() {
		return named("my")
				.withCalls(restCall(Method.GET, "/api/my/:id", this::my))
				.withAutoAcl(true);	
	}
}
