package com.ethereal.learning.functional;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ethereal.learning.model.Employee;
import com.ethereal.learning.repository.EmployeeRepository;

@Configuration
public class EmployeeFunctionalConfig {

	@Bean
	EmployeeRepository employeeRepository() {
		return new EmployeeRepository();
	}

	@Bean
	RouterFunction<ServerResponse> getAllEmployeesRoute() {
		return route(GET("/employees"), req -> ok().body(employeeRepository().findAllEmployees(), Employee.class));
	}

	@Bean
	RouterFunction<ServerResponse> getEmployeeByIdRoute() {
		return route(GET("/employees/{id}"),
				req -> ok().body(employeeRepository().findEmployeeById(req.pathVariable("id")), Employee.class));
	}

	@Bean
	RouterFunction<ServerResponse> updateEmployeeRoute() {
		return route(POST("/employees/update"), req -> req.body(toMono(Employee.class))
				.doOnNext(employeeRepository()::updateEmployee).then(ok().build()));
	}

	@Bean
	RouterFunction<ServerResponse> composedRoutes() {
		return route(GET("/employees"), req -> ok().body(employeeRepository().findAllEmployees(), Employee.class))

				.and(route(GET("/employees/{id}"),
						req -> ok().body(employeeRepository().findEmployeeById(req.pathVariable("id")),
								Employee.class)))

				.and(route(POST("/employees/update"), req -> req.body(toMono(Employee.class))
						.doOnNext(employeeRepository()::updateEmployee).then(ok().build())));
	}

}