package com.coc.payments.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentRequest {
    
    @Id
    @PrimaryKey("idempotency_key")
    private String idempotencyKey;
    
    @Column("payment_id")
    private String paymentId;
    
    @Column("payment_status")
    private String paymentStatus;
    
    @Column("auth_url")
    private String authUrl;
    
}
