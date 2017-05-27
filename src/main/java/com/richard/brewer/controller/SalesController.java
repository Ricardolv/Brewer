package com.richard.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.richard.brewer.model.Beer;
import com.richard.brewer.repository.Beers;
import com.richard.brewer.session.TableSalesItems;

@Controller
@RequestMapping("/sales")
public class SalesController {
	
	@Autowired
	private Beers beers;
	
	@Autowired
	private TableSalesItems tableSalesItems;
	
	@GetMapping("/new")
	public ModelAndView newSale() {
		ModelAndView mv = new ModelAndView("sale/register-sales");
		return mv;
	}
	
	@PostMapping("/item")
	public @ResponseBody ModelAndView addItem(Long codeBeer) {
		
		Beer beer = beers.findOne(codeBeer);
		tableSalesItems.addItem(beer, 1);
		ModelAndView mv = new ModelAndView("sale/table-sale-items");
		mv.addObject("items", tableSalesItems.getItems());
		return mv;
	}
	
	@PutMapping("/item/{codeBeer}")
	public ModelAndView alterAmountItem(@PathVariable Long codeBeer, Integer quantity) {
		Beer beer = beers.findOne(codeBeer);
		tableSalesItems.alterAmountItems(beer, quantity);
		ModelAndView mv = new ModelAndView("sale/table-sale-items");
		mv.addObject("items", tableSalesItems.getItems());
		return mv;
	}
	

}
