package com.ethereal.learning.controller;


import java.time.LocalDateTime;

import org.jobrunr.scheduling.JobScheduler;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ethereal.learning.service.JobService;


@RestController
@RequestMapping("/jobrunr")
public class JobController {

	private JobScheduler jobScheduler;
	private JobService jobService;

	private JobController(JobScheduler jobScheduler, JobService jobService) {
		this.jobScheduler = jobScheduler;
		this.jobService = jobService;
	}

	@GetMapping(value = "/enqueue/{input}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> enqueue(
			@PathVariable("input") @DefaultValue("default-input") String input) {
		jobScheduler.enqueue(() -> jobService.executeSampleJob(input));
		return okResponse("Job enqueued successfully");
	}

	@GetMapping(value = "/schedule/{input}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> schedule(
			@PathVariable("input") @DefaultValue("default-input") String input,
			@RequestParam("scheduleAt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime scheduleAt) {
		jobScheduler.schedule(() -> jobService.executeSampleJob(input), scheduleAt);
		return okResponse("Job scheduled successfully");
	}

	private ResponseEntity<String> okResponse(String feedback) {
		return new ResponseEntity<>(feedback, HttpStatus.OK);
	}
}