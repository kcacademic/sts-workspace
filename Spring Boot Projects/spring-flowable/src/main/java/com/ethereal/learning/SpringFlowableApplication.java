package com.ethereal.learning;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ethereal.learning.service.MyService;

@SpringBootApplication
public class SpringFlowableApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFlowableApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(final RepositoryService repositoryService, final TaskService taskService,
			final MyService myService) {

		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				System.out.println(
						"Number of process definitions : " + repositoryService.createProcessDefinitionQuery().count());
				System.out.println("Number of tasks : " + taskService.createTaskQuery().count());

				myService.createDemoUsers();
			}
		};
	}

}
