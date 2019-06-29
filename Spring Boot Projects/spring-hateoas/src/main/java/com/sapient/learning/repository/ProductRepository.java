package com.sapient.learning.repository;

import org.springframework.data.repository.CrudRepository;

import com.sapient.learning.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}