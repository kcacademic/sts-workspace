package com.coc.payments.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType;

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
public class PaymentRecord {
    
    @Id
    @PrimaryKey("id")
    private String id;
    
    @Column("payer_id")
    private String payerId;
    
    @Column("user_id")
    private String userId;
    
    @Column("idempotency_key")
    private String idempotencyKey;
    
    @Column("intent")
    private String intent;
    
    @Column("payment_provider")
    private String paymentProvider;
    
    @Column("payment_method")
    private String paymentMethod;
    
    @Column("description")
    private String description;
    
    @Column("total")
    private String total;
    
    @Column("sub_total")
    private String subTotal;
    
    @Column("shipping")
    private String shipping;
    
    @Column("tax")
    private String tax;
    
    @Column("currency")
    private String currency;
    
    @Column("payment_status")
    private String paymentStatus;
    
    @CassandraType(type = DataType.Name.UDT, userTypeName = "transaction") 
    private Set<Operation> transactions = new HashSet<>();

}
