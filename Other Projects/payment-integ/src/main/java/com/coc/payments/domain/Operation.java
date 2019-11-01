package com.coc.payments.domain;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import com.datastax.driver.core.DataType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@UserDefinedType("transaction")
@Getter
@Setter
@NoArgsConstructor
public class Operation {
    
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;
}