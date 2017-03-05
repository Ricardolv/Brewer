package com.richard.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.model.Beer;

@Controller
@RequestMapping("/beers")
public class BeersController {
	
	@GetMapping("/new")
	public String newBeer(Beer beer) {
		return "beer/RegisterBeers";
	}
	
	@PostMapping("/new")
	public String register(@Valid Beer beer, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return newBeer(beer);
		}
		
		attributes.addFlashAttribute("message", "Cerveja salva com sucesso!");
		System.out.println("-> sku " + beer.getSku());
		System.out.println("-> nome " + beer.getName());
		System.out.println("-> descricao " + beer.getDescription());
		System.out.println("-> cadastrar");
		
		return "redirect:/beers/new";
	}

}
