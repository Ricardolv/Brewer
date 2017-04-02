package com.richard.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.model.Client;

@Controller
@RequestMapping("/clients")
public class ClientController {
	
	@GetMapping("/new")
	public String newClient(Client client) {
		return "client/register-clients";
	}
	
	@PostMapping("/new")
	public String register(@Valid Client client, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return newClient(client);
		}
		
		return "redirect:/clients/new";
	}

}
