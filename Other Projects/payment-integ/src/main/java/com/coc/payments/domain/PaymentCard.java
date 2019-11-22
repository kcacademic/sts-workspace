package com.coc.payments.domain;

import javax.validation.constraints.NotBlank;

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
public class PaymentCard {
    
    @Id
    @PrimaryKey("user_id")
    private String userId;
    
    @Column("id")
    @NotBlank(message = "Entry id is mandatory")
    private String id;
    
    @Column("number")
    @NotBlank(message = "Card number is mandatory")
    private String number;
    
    @Column("expiration_year")
    @NotBlank(message = "Card expiration year is mandatory")
    private String expirationYear;
    
    @Column("expiration_month")
    @NotBlank(message = "Card expiration month is mandatory")
    private String expirationMonth;
    
}
