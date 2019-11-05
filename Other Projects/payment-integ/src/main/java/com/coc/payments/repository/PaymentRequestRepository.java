package com.coc.payments.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.coc.payments.domain.PaymentRequest;

public interface PaymentRequestRepository extends CassandraRepository<PaymentRequest, String> {

}