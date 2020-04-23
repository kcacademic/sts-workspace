package com.sapient.learning.custom.play;

import static java.util.stream.Collectors.joining;
import static play.mvc.Results.ok;

import java.io.File;

import javax.inject.Inject;

import play.api.mvc.Handler;
import play.api.mvc.RequestHeader;
import play.api.routing.Router;
import play.api.routing.SimpleRouter;
import play.mvc.Http;
import play.routing.RoutingDsl;
import scala.PartialFunction;

public class FileUploadRouter implements SimpleRouter {

	private final Router delegate;

	@Inject
	public FileUploadRouter(RoutingDsl routingDsl) {
		this.delegate = routingDsl.POST("/api/files").routingTo(request -> {
			Http.MultipartFormData<File> body = request.body().asMultipartFormData();
			String response = body.getFiles().stream().map(f -> f.getRef().getAbsolutePath())
					.collect(joining(",", "Uploaded[", "]"));
			return ok(response);
		}).build().asScala();
	}

	@Override
	public PartialFunction<RequestHeader, Handler> routes() {
		return delegate.routes();
	}
}