package com.sapient.learning.custom.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;
import static com.lightbend.lagom.javadsl.api.ServiceAcl.path;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.sapient.learning.api.akka.Job;
import com.sapient.learning.api.akka.JobAccepted;
import com.sapient.learning.api.akka.JobStatus;

import akka.NotUsed;
import akka.stream.javadsl.Source;

public interface CustomService extends Service {

	ServiceCall<NotUsed, String> custom(String id);

	ServiceCall<Job, JobAccepted> doWork();
	
	ServiceCall<NotUsed, Source<JobStatus, ?>> status();

	@Override
	default Descriptor descriptor() {
		return named("custom")
				.withCalls(
						pathCall("/api/custom/:id", this::custom), 
						pathCall("/dowork", this::doWork),
						pathCall("/status", this::status))
				.withAutoAcl(true)
				.withServiceAcls(path("/api/play"));
	}

	

}
