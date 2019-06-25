package com.sapient.learning.repository;

import org.springframework.data.repository.CrudRepository;

import com.sapient.learning.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
