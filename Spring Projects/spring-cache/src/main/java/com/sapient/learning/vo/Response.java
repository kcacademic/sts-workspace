package com.sapient.learning.vo;

import org.springframework.http.HttpStatus;
import com.sapient.learning.model.InvoiceHeader;

import lombok.Data;

@Data
public class Response {
	long interval;
	HttpStatus httpStatus;
	InvoiceHeader invoiceHeader;
}
