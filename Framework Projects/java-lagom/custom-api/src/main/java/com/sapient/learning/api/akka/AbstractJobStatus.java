package com.sapient.learning.api.akka;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.immutable.ImmutableStyle;
import com.lightbend.lagom.serialization.Jsonable;

import org.immutables.value.Value;

@Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = JobStatus.class)
public interface AbstractJobStatus extends Jsonable {
	@Value.Parameter
	public String getJobId();

	@Value.Parameter
	public String getStatus();
}
