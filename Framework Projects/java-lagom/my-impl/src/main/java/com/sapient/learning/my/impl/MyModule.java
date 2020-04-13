package com.sapient.learning.my.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.sapient.learning.custom.api.CustomService;
import com.sapient.learning.my.api.MyService;

public class MyModule extends AbstractModule implements ServiceGuiceSupport {
	@Override
	protected void configure() {
		bindService(MyService.class, MyServiceImpl.class);
		bindClient(CustomService.class);
	}
}
