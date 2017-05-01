package com.richard.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.model.Client;
import com.richard.brewer.model.PersonType;
import com.richard.brewer.service.StateService;

@Controller
@RequestMapping("/clients")
public class ClientsController {
	
	@Autowired
	private StateService stateService;
	
	@GetMapping("/new")
	public ModelAndView newClient(Client client) {
		ModelAndView mv = new ModelAndView("client/register-clients");
		
		mv.addObject("personTypes", PersonType.values());
		mv.addObject("states", stateService.findAll());
		return mv;
	}
	
	@PostMapping("/new")
	public ModelAndView register(@Valid Client client, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return newClient(client);
		}
		
		return new ModelAndView("redirect:/clients/new");
	}
	
}
