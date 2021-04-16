package com.ethereal.learning.repository;

import org.springframework.data.repository.CrudRepository;

import com.ethereal.learning.model.CustomerOrder;

public interface OrderRepository extends CrudRepository<CustomerOrder, Long> {

}
