package com.ethereal.learning.vo;

import org.springframework.http.HttpStatus;

import com.ethereal.learning.model.InvoiceHeader;

import lombok.Data;

@Data
public class Response {
	long interval;
	HttpStatus httpStatus;
	InvoiceHeader invoiceHeader;
}
