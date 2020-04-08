package com.sapient.learning.spring.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sapient.learning.spring.jpa.config.Config;
import com.sapient.learning.spring.jpa.inventory.InventoryConfig;
import com.sapient.learning.spring.jpa.order.OrderConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class, InventoryConfig.class, OrderConfig.class })
public class ApplicationUnitTest {

	@Autowired
	Application application;

	@Test
	public void testPlaceOrder() throws Exception {

		application.placeOrder("7", "1", 1);

	}
}
