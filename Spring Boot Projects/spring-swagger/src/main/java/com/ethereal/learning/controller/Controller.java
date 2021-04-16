package com.ethereal.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@Api(tags = "Sample API")
public class Controller {
	
	@GetMapping("/hello")
	@ApiOperation(value = "Sample hello world operation!")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"), 
	                        @ApiResponse(code = 500, message = "An unexpected error occurred") 
	                      })
	public String hello() {
		return "Hello World!";
	}

}
