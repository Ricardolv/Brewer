package com.richard.brewer.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import com.richard.brewer.controller.page.PageWrapper;
import com.richard.brewer.controller.validator.SaleValidator;
import com.richard.brewer.mail.Mailer;
import com.richard.brewer.model.Beer;
import com.richard.brewer.model.PersonType;
import com.richard.brewer.model.Sale;
import com.richard.brewer.model.SaleStatus;
import com.richard.brewer.repository.Beers;
import com.richard.brewer.repository.Sales;
import com.richard.brewer.repository.filter.SaleFilter;
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
	
	@Autowired
	private Sales sales;
	
	@Autowired
	private Mailer mailer;
	
	@InitBinder("sale")
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
	
	@PostMapping(value = "/new", params = "save")
	public ModelAndView save(Sale sale, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UserSystem userSystem) {
		validatorSale(sale, result);
		if (result.hasErrors()) {
			return newSale(sale);
		}
		
		sale.setUser(userSystem.getUser());
		
		salesService.save(sale);
		attributes.addFlashAttribute("message", "Venda salva com sucesso!");
		return new ModelAndView("redirect:/sales/new");
	}

	@PostMapping(value = "/new", params = "issue")
	public ModelAndView issue(Sale sale, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UserSystem userSystem) {
		validatorSale(sale, result);
		if (result.hasErrors()) {
			return newSale(sale);
		}
		
		sale.setUser(userSystem.getUser());
		
		salesService.issue(sale);
		attributes.addFlashAttribute("message", "Venda emitida com sucesso!");
		return new ModelAndView("redirect:/sales/new");
	}
	
	@PostMapping(value = "/new", params = "sendEmail")
	public ModelAndView sendEmail(Sale sale, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UserSystem userSystem) {
		validatorSale(sale, result);
		if (result.hasErrors()) {
			return newSale(sale);
		}
		
		sale.setUser(userSystem.getUser());
		
		salesService.save(sale);
		mailer.send();
		System.out.println("logo depois da chamada enviae email enviado.....");
		
		attributes.addFlashAttribute("message", "Venda salva e e-mail enviado!");
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
	
	@GetMapping
	public ModelAndView search(SaleFilter saleFilter,
			@PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("sale/search-sales");
		mv.addObject("statusAll", SaleStatus.values());
		mv.addObject("personTypes", PersonType.values());
		
		PageWrapper<Sale> paginaWrapper = new PageWrapper<>(sales.filter(saleFilter, pageable)
				, httpServletRequest);
		mv.addObject("page", paginaWrapper);
		return mv;
	}

	private ModelAndView mvTableSaleItems(String uuid) {
		ModelAndView mv = new ModelAndView("sale/table-sale-items");
		mv.addObject("items", tableSalesItems.getItems(uuid));
		mv.addObject("totalValue", tableSalesItems.getTotalValue(uuid));
		return mv;
	}
	
	private void validatorSale(Sale sale, BindingResult result) {
		sale.addItems(tableSalesItems.getItems(sale.getUuid()));
		sale.calculateTotalValue();
		
		saleValidator.validate(sale, result);
	}
	
	
	

}
