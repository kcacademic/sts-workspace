package com.sapient.learning.repository;

import org.springframework.data.repository.CrudRepository;

import com.sapient.learning.model.CustomerOrder;

public interface OrderRepository extends CrudRepository<CustomerOrder, Long> {

}
