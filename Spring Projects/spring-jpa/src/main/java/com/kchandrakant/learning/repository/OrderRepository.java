package com.kchandrakant.learning.repository;

import org.springframework.data.repository.CrudRepository;

import com.kchandrakant.learning.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
