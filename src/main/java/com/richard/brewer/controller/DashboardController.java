package com.richard.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.richard.brewer.service.SalesService;

@Controller
public class DashboardController {
	
	@Autowired
	private SalesService salesService;
	
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("dashboard");
		
		mv.addObject("salesTotalValueYear", salesService.totalValueYear());
		mv.addObject("salesTotalValueMonth", salesService.totalValueMonth());
		mv.addObject("tickedValue", salesService.tickedValue());
		
		mv.addObject("clientsQuantity", salesService.clientsQuantity());
		mv.addObject("stockQuantity", salesService.stockQuantity());
		mv.addObject("stockTotalValue", salesService.stockTotalValue());
		
		return mv;
	}

}
