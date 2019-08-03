package com.sapient.learning.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sapient.learning.service.MyService;

@Controller
public class MyController {
	
	@Autowired
	MyService service;

	@RequestMapping("/")
	public String index(Map<String, Object> model) throws Exception {
        String content = service.serve();
		model.put("content", content);
		return "index";
    }
}
