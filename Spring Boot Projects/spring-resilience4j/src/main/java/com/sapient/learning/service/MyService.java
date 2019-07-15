package com.sapient.learning.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import reactor.core.publisher.Mono;

@Service
public class MyService {

	private static final String DATE_SERVICE = "dateService";

	@CircuitBreaker(name = DATE_SERVICE, fallbackMethod = "dateServiceFallback")
	@RateLimiter(name = DATE_SERVICE)
	@Bulkhead(name = DATE_SERVICE)
	@Retry(name = DATE_SERVICE, fallbackMethod = "dateServiceFallback")
	public Mono<Date> dateService(String param) {

		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		Date date;
		try {
			date = format.parse(param);
		} catch (ParseException e) {
			return Mono.error(e);
		}
		return Mono.just(date);
	}

	@SuppressWarnings("unused")
	private Mono<Date> dateServiceFallback(String param, RuntimeException e) {
		return Mono.just(new Date());
	}

	private static final String NAME_SERVICE = "nameService";

	@CircuitBreaker(name = NAME_SERVICE, fallbackMethod = "nameServiceFallback")
	@RateLimiter(name = NAME_SERVICE)
	@Bulkhead(name = NAME_SERVICE)
	@Retry(name = NAME_SERVICE, fallbackMethod = "nameServiceFallback")
	public Mono<String> nameService(String param) {
		if(param == null)
			return Mono.error(new Exception());
		return Mono.just("Mr. " + param.toLowerCase());
	}

	@SuppressWarnings("unused")
	private Mono<String> nameServiceFallback(String param, RuntimeException e) {
		return Mono.just("No name has been provided.");
	}

}
