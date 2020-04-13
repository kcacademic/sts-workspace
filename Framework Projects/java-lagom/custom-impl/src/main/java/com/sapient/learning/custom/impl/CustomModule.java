package com.sapient.learning.custom.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.sapient.learning.custom.akka.Worker;
import com.sapient.learning.custom.api.CustomService;

import play.libs.akka.AkkaGuiceSupport;

public class CustomModule extends AbstractModule implements ServiceGuiceSupport, AkkaGuiceSupport {
	@Override
	protected void configure() {
		bindService(CustomService.class, CustomServiceImpl.class);
		bindActor(Worker.class, "worker");
	}
}
