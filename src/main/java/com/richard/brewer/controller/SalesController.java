package com.richard.brewer.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.richard.brewer.model.Beer;
import com.richard.brewer.repository.Beers;
import com.richard.brewer.session.TableItemsSession;

@Controller
@RequestMapping("/sales")
public class SalesController {
	
	@Autowired
	private Beers beers;
	
	@Autowired
	private TableItemsSession tableSalesItems;
	
	@GetMapping("/new")
	public ModelAndView newSale() {
		ModelAndView mv = new ModelAndView("sale/register-sales");
		mv.addObject("uuid", UUID.randomUUID().toString());
		return mv;
	}
	
	@PostMapping("/item")
	public @ResponseBody ModelAndView addItem(Long codeBeer, String uuid) {
		
		Beer beer = beers.findOne(codeBeer);
		tableSalesItems.addItem(uuid, beer, 1);
		return mvTableSaleItems(uuid);
	}
	
	@PutMapping("/item/{codeBeer}")
	public ModelAndView alterAmountItem(@PathVariable("codeBeer") Beer beer, Integer quantity, String uuid) {
		tableSalesItems.alterAmountItems(uuid, beer, quantity);
		return mvTableSaleItems(uuid);
	}
	
	@DeleteMapping("/item/{uuid}/{codeBeer}")
	public ModelAndView deleteItem(@PathVariable("uuid") String uuid, @PathVariable("codeBeer") Beer beer) {
		tableSalesItems.deleteItem(uuid, beer);
		return mvTableSaleItems(uuid);
	}

	private ModelAndView mvTableSaleItems(String uuid) {
		ModelAndView mv = new ModelAndView("sale/table-sale-items");
		mv.addObject("items", tableSalesItems.getItems(uuid));
		mv.addObject("totalValue", tableSalesItems.getTotalValue(uuid));
		return mv;
	}
	

}
