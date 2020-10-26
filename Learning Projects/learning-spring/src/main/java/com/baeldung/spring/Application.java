package com.baeldung.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.baeldung.spring.beanlifecycle.MyBean;
import com.baeldung.spring.config.SpringConfig;
import com.baeldung.spring.config.SpringConfigYaml;
import com.baeldung.spring.spel.CustomerConfig;

public class Application {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.baeldung.spring");

		MyBean myBean = context.getBean(MyBean.class);
		System.out.println(myBean);

		CustomerConfig customerConfig = context.getBean(CustomerConfig.class);
		System.out.println(customerConfig.getCustomerNames());
		System.out.println(customerConfig.getCustomerAge());

		SpringConfig springConfig = context.getBean(SpringConfig.class);
		System.out.println(springConfig.getValueFromString());
		System.out.println(springConfig.getValuesFromMap());

		SpringConfigYaml springConfigYaml = context.getBean(SpringConfigYaml.class);
		System.out.println(springConfigYaml.getValueFromString());
		System.out.println(springConfigYaml.getValuesFromMap());

		context.close();

	}

}
