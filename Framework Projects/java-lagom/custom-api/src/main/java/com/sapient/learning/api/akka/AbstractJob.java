package com.sapient.learning.api.akka;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.immutable.ImmutableStyle;
import com.lightbend.lagom.serialization.Jsonable;

import org.immutables.value.Value;

@Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = Job.class)
public interface AbstractJob extends Jsonable {
	@Value.Parameter
	public String getJobId();

	@Value.Parameter
	public String getTask();

	@Value.Parameter
	public String getPayload();
}