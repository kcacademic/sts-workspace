package com.ethereal.learning.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ethereal.learning.exception.MyException;

@Controller
public class GreetingController {

	@GetMapping("/greeting/{id}")
	public String greeting(HttpServletResponse response, @PathVariable boolean id) throws IOException, MyException {

		if (id) {
			// response.sendError(HttpServletResponse.SC_FORBIDDEN);
			response.setHeader("location", "https://google.com");
			response.getWriter().write("{\"location\":\"https://google.com\"}");
			throw new MyException();
		} else {
			// response.sendRedirect("/message");
			return "redirect:/message";
		}

		// return "greeting";
	}

	@GetMapping("/message")
	public String greeting(Model model) throws IOException {

		model.addAttribute("message", "Kumar Chandrakant");
		return "message";
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(MyException.class)
	public void myExceptionHandler() {

	}

}
