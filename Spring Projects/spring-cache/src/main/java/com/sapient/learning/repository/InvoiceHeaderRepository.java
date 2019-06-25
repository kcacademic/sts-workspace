package com.sapient.learning.repository;

import org.springframework.data.repository.CrudRepository;

import com.sapient.learning.model.InvoiceHeader;

public interface InvoiceHeaderRepository extends CrudRepository<InvoiceHeader, Integer> {

}