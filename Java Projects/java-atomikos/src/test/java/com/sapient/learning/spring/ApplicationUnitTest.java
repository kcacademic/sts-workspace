package com.sapient.learning.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sapient.learning.spring.config.Config;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class })
public class ApplicationUnitTest {

	@Autowired
	Application application;

	@Test
	public void testPlaceOrder() throws Exception {

		application.placeOrder("3", "1", 1);

	}
}
