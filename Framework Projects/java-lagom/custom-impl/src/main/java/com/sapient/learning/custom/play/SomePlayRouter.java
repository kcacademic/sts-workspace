package com.sapient.learning.custom.play;

import static play.mvc.Results.ok;

import javax.inject.Inject;

import play.api.mvc.Handler;
import play.api.mvc.RequestHeader;
import play.api.routing.Router;
import play.api.routing.SimpleRouter;
import play.routing.RoutingDsl;
import scala.PartialFunction;

public class SomePlayRouter implements SimpleRouter {
	
	private final Router delegate;
	
	@Inject
	public SomePlayRouter(RoutingDsl routingDsl) {
		this.delegate = routingDsl.POST("/api/play").routingTo(request -> {
			return ok("Response from Play Simple Router");
		}).build().asScala();
	}
	
	@Override
	public PartialFunction<RequestHeader, Handler> routes() {
		return delegate.routes();
	}
}