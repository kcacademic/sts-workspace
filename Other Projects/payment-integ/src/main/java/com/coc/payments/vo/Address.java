package com.coc.payments.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class Address {

    private String name;
    private String line1;
    private String line2;
    private String city;
    private String countryCode;
    private String postCode;
    private String phone;
    private String state;

}
