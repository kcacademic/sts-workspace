package com.coc.payments.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.coc.payments.domain.PaymentRecord;

public interface PaymentRecordRepository extends CassandraRepository<PaymentRecord, String> {

}