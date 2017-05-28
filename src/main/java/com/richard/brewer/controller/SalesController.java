package com.richard.brewer.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.model.Beer;
import com.richard.brewer.model.Sale;
import com.richard.brewer.repository.Beers;
import com.richard.brewer.security.UserSystem;
import com.richard.brewer.service.SalesService;
import com.richard.brewer.session.TableItemsSession;

@Controller
@RequestMapping("/sales")
public class SalesController {
	
	@Autowired
	private Beers beers;
	
	@Autowired
	private TableItemsSession tableSalesItems;
	
	@Autowired
	private SalesService salesService;
	
	@GetMapping("/new")
	public ModelAndView newSale(Sale sale) {
		ModelAndView mv = new ModelAndView("sale/register-sales");
		sale.setUuid(UUID.randomUUID().toString());
		return mv;
	}
	
	@PostMapping("/new")
	public ModelAndView save(Sale sale, RedirectAttributes attributes, @AuthenticationPrincipal UserSystem userSystem) {
		sale.setUser(userSystem.getUser());
		sale.addItems(tableSalesItems.getItems(sale.getUuid()));
		
		salesService.save(sale);
		attributes.addFlashAttribute("message", "Venda salva com sucesso!");
		return new ModelAndView("redirect:/sales/new");
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
