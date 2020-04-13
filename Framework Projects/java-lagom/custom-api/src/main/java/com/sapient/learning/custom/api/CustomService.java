package com.sapient.learning.custom.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.sapient.learning.api.akka.Job;
import com.sapient.learning.api.akka.JobAccepted;

import akka.NotUsed;

public interface CustomService extends Service {

	ServiceCall<NotUsed, String> custom(String id);

	ServiceCall<Job, JobAccepted> doWork();

	@Override
	default Descriptor descriptor() {
		return named("custom")
				.withCalls(
						pathCall("/api/custom/:id", this::custom), 
						pathCall("/dowork", this::doWork))
				.withAutoAcl(true);
	}

}
