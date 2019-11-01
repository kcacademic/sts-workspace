package com.coc.payments.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.coc.payments.domain.PaymentRecord;

public interface PaymentRepository extends CassandraRepository<PaymentRecord, UUID>{}