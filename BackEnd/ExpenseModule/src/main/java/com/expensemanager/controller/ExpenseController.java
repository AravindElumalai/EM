package com.expensemanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

	private Logger logger = LoggerFactory.getLogger(ExpenseController.class);
	
	
	
	@GetMapping(path = "/expense")
	public String sayHello() {
		return "Hello";
	}
}
