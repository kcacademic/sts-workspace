package com.ethereal.learning.repository;

import org.springframework.data.repository.CrudRepository;

import com.ethereal.learning.model.InvoiceHeader;

public interface InvoiceHeaderRepository extends CrudRepository<InvoiceHeader, Integer> {

}