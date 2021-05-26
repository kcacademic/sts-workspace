package com.kchandrakant.learning.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.kchandrakant.learning.processor.LinesProcessor;
import com.kchandrakant.learning.reader.LinesReader;
import com.kchandrakant.learning.writer.LinesWriter;

public class SpringBatchTaskletConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public LinesReader linesReader() {
		return new LinesReader();
	}

	@Bean
	public LinesProcessor linesProcessor() {
		return new LinesProcessor();
	}

	@Bean
	public LinesWriter linesWriter() {
		return new LinesWriter();
	}

	@Bean
	protected Step readLines() {
		return stepBuilderFactory.get("readLines").tasklet(linesReader()).build();
	}

	@Bean
	protected Step processLines() {
		return stepBuilderFactory.get("processLines").tasklet(linesProcessor()).build();
	}

	@Bean
	protected Step writeLines() {
		return stepBuilderFactory.get("writeLines").tasklet(linesWriter()).build();
	}

	@Bean(name = "taskletsJob")
	public Job job() {
		return jobBuilderFactory.get("taskletsJob").start(readLines()).next(processLines()).next(writeLines()).build();
	}

}
