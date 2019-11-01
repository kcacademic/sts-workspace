package com.coc.payments.event;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PaymentEvent {
    
    private UUID id;

}
