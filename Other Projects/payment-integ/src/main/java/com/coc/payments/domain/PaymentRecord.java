package com.coc.payments.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Getter
@Setter
@NoArgsConstructor
public class PaymentRecord {
    
    @Id
    @PrimaryKeyColumn
    private UUID id;
    
    @CassandraType(type = DataType.Name.UDT, userTypeName = "transaction") 
    private Set<Operation> transactions = new HashSet<>();

}
