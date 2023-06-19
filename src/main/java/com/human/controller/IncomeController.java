package com.human.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IncomeController {

	@RequestMapping("/input/income")
	public String inputIncome() {

		return "input/income";
	}
	
//	@PostMapping
//	public String saveIncome(@RequestParam("income") int income) {
//		
//		return "redirect:/input/income";
//	}
}
