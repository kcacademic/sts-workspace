package com.coc.payments.domain;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import com.datastax.driver.core.DataType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@UserDefinedType("transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Operation {
    
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;
    
    private String type;
}