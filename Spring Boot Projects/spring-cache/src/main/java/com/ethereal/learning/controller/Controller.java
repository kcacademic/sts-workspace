package com.ethereal.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ethereal.learning.service.CacheData;
import com.ethereal.learning.vo.Request;
import com.ethereal.learning.vo.Response;

@RestController
public class Controller {

	@Autowired
	CacheData cacheData;

	@GetMapping("/{id}")
	public ResponseEntity<Response> get(@PathVariable int id) {
		long timeInit = System.currentTimeMillis();
		Response response = cacheData.getDataCache(id);
		long timeEnd = System.currentTimeMillis();
		response.setInterval(timeEnd - timeInit);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<Response> put(@RequestBody Request dtoRequest) {
		cacheData.update(dtoRequest);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping("/flushcache")
	public String flushCache() {
		cacheData.flushCache();
		return "Cache Flushed!";
	}
}