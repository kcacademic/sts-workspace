package com.baeldung.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.baeldung.springboot.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    
    List<Employee> findAll();

}
