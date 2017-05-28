package com.richard.brewer.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.controller.validator.SaleValidator;
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
	
	@Autowired
	private SaleValidator saleValidator;
	
	@InitBinder
	public void initializeValidator(WebDataBinder binder) {
		binder.setValidator(saleValidator);
	}
	
	@GetMapping("/new")
	public ModelAndView newSale(Sale sale) {
		ModelAndView mv = new ModelAndView("sale/register-sales");
		
		if (StringUtils.isEmpty(sale.getUuid())) {
			sale.setUuid(UUID.randomUUID().toString());
		}
		
		mv.addObject("items", sale.getItems());
		mv.addObject("freightValue", sale.getFreightValue());
		mv.addObject("discountValue", sale.getDiscountValue());
		mv.addObject("totalValueItems", tableSalesItems.getTotalValue(sale.getUuid()));
		
		return mv;
	}
	
	@PostMapping("/new")
	public ModelAndView save(Sale sale, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UserSystem userSystem) {
		sale.addItems(tableSalesItems.getItems(sale.getUuid()));
		sale.calculateTotalValue();
		
		saleValidator.validate(sale, result);
		if (result.hasErrors()) {
			return newSale(sale);
		}
		
		sale.setUser(userSystem.getUser());
		
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
