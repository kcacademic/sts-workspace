package com.sapient.learning.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.monitorjbl.json.JsonResult;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.Match;
import com.sapient.learning.model.Address;
import com.sapient.learning.model.Customer;

@RestController
public class MyController {

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public List<Customer> getCustomers() {

		Customer customer = new Customer("Joe", "Smith", LocalDate.of(1982, 1, 10),
				new Address("High Street", "Newry", "Down", "BT893PY"));

		List<Customer> list = Arrays.asList(customer);

		return list;
	}

	@RequestMapping(value = "/customers-only", method = RequestMethod.GET)
	public List<Customer> getCustomersWithoutAddress() {

		Customer customer = new Customer("Joe", "Smith", LocalDate.of(1982, 1, 10),
				new Address("High Street", "Newry", "Down", "BT893PY"));

		List<Customer> list = Arrays.asList(customer);

		JsonResult json = JsonResult.instance();

		json.use(JsonView.with(list).onClass(Address.class, Match.match().exclude("*")));

		return list;
	}

}