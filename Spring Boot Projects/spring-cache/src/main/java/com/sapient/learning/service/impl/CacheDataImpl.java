package com.sapient.learning.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.sapient.learning.vo.Request;
import com.sapient.learning.vo.Response;
import com.sapient.learning.model.InvoiceHeader;
import com.sapient.learning.repository.InvoiceHeaderRepository;
import com.sapient.learning.service.CacheData;

@Component
public class CacheDataImpl implements CacheData {
	@Autowired
	InvoiceHeaderRepository invoiceHeaderRepository;

	@Override
	@Cacheable(cacheNames = "headers", condition = "#id > 1")
	public Response getDataCache(int id) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Response requestResponse = new Response();
		Optional<InvoiceHeader> invoice = invoiceHeaderRepository.findById(id);

		if (invoice.isPresent()) {
			requestResponse.setInvoiceHeader(invoice.get());
			requestResponse.setHttpStatus(HttpStatus.OK);
			return requestResponse;
		}
		requestResponse.setHttpStatus(HttpStatus.NOT_FOUND);
		return requestResponse;
	}

	@Override
	@CacheEvict(cacheNames = "headers", allEntries = true)
	public void flushCache() {
	}

	@CachePut(cacheNames = "headers", key = "#dtoRequest.id")
	public Response update(Request dtoRequest) {
		Response requestResponse = new Response();
		Optional<InvoiceHeader> invoiceOptional = invoiceHeaderRepository.findById(dtoRequest.getId());
		if (!invoiceOptional.isPresent()) {
			requestResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
			return requestResponse;
		}
		InvoiceHeader invoice = invoiceOptional.get();
		invoice.setActivo(dtoRequest.getActive());
		invoiceHeaderRepository.save(invoice);

		requestResponse.setInvoiceHeader(invoice);
		requestResponse.setHttpStatus(HttpStatus.OK);
		return requestResponse;
	}

}
