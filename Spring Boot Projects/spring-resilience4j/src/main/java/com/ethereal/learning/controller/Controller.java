package com.ethereal.learning.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ethereal.learning.service.MyService;

import reactor.core.publisher.Mono;

@RestController
public class Controller {

	@Autowired
	private MyService myService;

	@GetMapping("/name/{name}")
	public Mono<String> getName(@PathVariable(value = "name") String name)
			throws ParseException {
		return myService.nameService(name);
	}
	
	@GetMapping("/date/{date}")
	public Mono<Date> getDate(@PathVariable(value = "date") String date)
			throws ParseException {
		return myService.dateService(date);
	}

}
