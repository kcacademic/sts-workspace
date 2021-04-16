package com.ethereal.learning.repository;

import org.springframework.data.repository.CrudRepository;

import com.ethereal.learning.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}