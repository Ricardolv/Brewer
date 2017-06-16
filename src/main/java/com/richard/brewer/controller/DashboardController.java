package com.richard.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.richard.brewer.repository.Sales;

@Controller
public class DashboardController {
	
	@Autowired
	private Sales sales;
	
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("dashboard");
		
		mv.addObject("salesTotalValueYear", sales.totalValueYear());
		mv.addObject("salesTotalValueMonth", sales.totalValueMonth());
		mv.addObject("tickedValue", sales.tickedValue());
		
		mv.addObject("clientsQuantity", sales.clientsQuantity());
		mv.addObject("stockQuantity", sales.stockQuantity());
		mv.addObject("stockTotalValue", sales.stockTotalValue());
		
		return mv;
	}

}
