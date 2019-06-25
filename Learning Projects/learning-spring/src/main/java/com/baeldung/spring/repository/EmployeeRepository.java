package com.baeldung.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.baeldung.spring.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    
    List<Employee> findAll();

}
