package com.sapient.learning;

import javax.annotation.PostConstruct;

import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sapient.learning.service.JobService;

@SpringBootApplication
public class Application {

	@Autowired
	private JobScheduler jobScheduler;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public StorageProvider storageProvider(JobMapper jobMapper) {
		InMemoryStorageProvider storageProvider = new InMemoryStorageProvider();
		storageProvider.setJobMapper(jobMapper);
		return storageProvider;
	}

	@PostConstruct
	public void scheduleRecurrently() {
		jobScheduler.<JobService>scheduleRecurrently(x -> x.executeSampleJob("a recurring job"), Cron.minutely());
	}

}