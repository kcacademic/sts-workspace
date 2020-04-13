package com.sapient.learning.my.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.sapient.learning.custom.api.CustomService;
import com.sapient.learning.my.api.MyService;

import akka.NotUsed;

public class MyServiceImpl implements MyService {

	private final CustomService customService;

	@Inject
	public MyServiceImpl(CustomService customService) {
		this.customService = customService;
	}

	@Override
	public ServiceCall<NotUsed, String> my(String id) {
		return request -> {
			System.out.println("My API Request: " + request);
			String response = null;
			try {
				response = customService.custom(id).invoke().toCompletableFuture().get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return CompletableFuture.completedFuture("My API wraps the response from Custom API: [" + response + "]");
		};
	}
}
