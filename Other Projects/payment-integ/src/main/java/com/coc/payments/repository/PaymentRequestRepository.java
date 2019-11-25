package com.coc.payments.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.coc.payments.domain.PaymentByRequestId;

public interface PaymentRequestRepository extends CassandraRepository<PaymentByRequestId, String> {

}