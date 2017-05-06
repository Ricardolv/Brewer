package com.richard.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.controller.page.PageWrapper;
import com.richard.brewer.model.Client;
import com.richard.brewer.model.PersonType;
import com.richard.brewer.repository.filter.ClientFilter;
import com.richard.brewer.service.ClientsService;
import com.richard.brewer.service.StateService;
import com.richard.brewer.service.exception.ClientCpfCnpjExistsException;

@Controller
@RequestMapping("/clients")
public class ClientsController {
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private ClientsService clientsService;
	
	@GetMapping("/new")
	public ModelAndView newClient(Client client) {
		ModelAndView mv = new ModelAndView("client/register-clients");
		
		mv.addObject("personTypes", PersonType.values());
		mv.addObject("states", stateService.findAll());
		return mv;
	}
	
	@PostMapping("/new")
	public ModelAndView save(@Valid Client client, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return newClient(client);
		}
		
		try {
			clientsService.save(client);
		} catch (ClientCpfCnpjExistsException e) {
			result.rejectValue("cpfCnpj", e.getMessage(), e.getMessage());
			return newClient(client);
		}
		
		attributes.addFlashAttribute("message", "Cliente salvo com sucesso!");
		return new ModelAndView("redirect:/clients/new");
	}
	
	@GetMapping
	public ModelAndView search(ClientFilter clientFilter, BindingResult result, 
			@PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("client/search-clients");
		mv.addObject("personTypes", PersonType.values());
		
		PageWrapper<Client> paginaWrapper = new PageWrapper<>(clientsService.filter(clientFilter, pageable), httpServletRequest);
		mv.addObject("page", paginaWrapper);
		return mv;
	}
	
}
