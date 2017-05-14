package com.richard.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sales")
public class SalesController {
	
	@GetMapping("/new")
	public ModelAndView newSale() {
		ModelAndView mv = new ModelAndView("sale/register-sales");
		
		
		return mv;
	}

}
