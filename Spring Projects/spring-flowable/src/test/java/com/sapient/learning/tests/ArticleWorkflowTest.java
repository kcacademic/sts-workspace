package com.sapient.learning.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.test.Deployment;
import org.flowable.spring.ProcessEngineFactoryBean;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.impl.test.FlowableSpringExtension;
import org.flowable.task.api.Task;
import org.h2.Driver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ExtendWith(FlowableSpringExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ArticleWorkflowTest.TestConfiguration.class)
public class ArticleWorkflowTest {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Test
	@Deployment (resources = {"processes/article-workflow.bpmn20.xml"})
	void articleApprovalTest() {

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("author", "test@dummy.com");
		variables.put("url", "http://test.com/dummy");
		runtimeService.startProcessInstanceByKey("articleReview", variables);
		Task task = taskService.createTaskQuery().singleResult();
		assertEquals("Review the submitted tutorial", task.getName());

		variables.put("approved", true);
		taskService.complete(task.getId(), variables);
		assertEquals(0, runtimeService.createProcessInstanceQuery().count());

	}

	@Configuration
	@EnableTransactionManagement
	static class TestConfiguration {

		@Bean
		public DataSource dataSource() {
			SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
			dataSource.setDriverClass(Driver.class);
			dataSource.setUrl("jdbc:h2:mem:flowable-jupiter;DB_CLOSE_DELAY=1000");
			dataSource.setUsername("sa");
			dataSource.setPassword("");

			return dataSource;
		}

		@Bean
		public PlatformTransactionManager transactionManager(DataSource dataSource) {
			return new DataSourceTransactionManager(dataSource);
		}

		@Bean
		public SpringProcessEngineConfiguration processEngineConfiguration(DataSource dataSource,
				PlatformTransactionManager transactionManager) {
			SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
			configuration.setDataSource(dataSource);
			configuration.setTransactionManager(transactionManager);
			configuration.setDatabaseSchemaUpdate("true");
			return configuration;
		}

		@Bean
		public ProcessEngineFactoryBean processEngine(SpringProcessEngineConfiguration processEngineConfiguration) {
			ProcessEngineFactoryBean factoryBean = new ProcessEngineFactoryBean();
			factoryBean.setProcessEngineConfiguration(processEngineConfiguration);
			return factoryBean;
		}

		@Bean
		public RuntimeService runtimeService(ProcessEngine processEngine) {
			return processEngine.getRuntimeService();
		}

		@Bean
		public TaskService taskService(ProcessEngine processEngine) {
			return processEngine.getTaskService();
		}
	}
}