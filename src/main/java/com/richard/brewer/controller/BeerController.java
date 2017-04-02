package com.richard.brewer.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.model.Beer;
import com.richard.brewer.repository.BeerRepository;

@Controller
@RequestMapping("/beers")
public class BeerController {
	
	private static final Logger logger = LoggerFactory.getLogger(BeerController.class);
	
	@Autowired
	private BeerRepository beerRepository;
	
	@GetMapping("/new")
	public String newBeer(Beer beer) {
		
		beerRepository.findAll();
		
		return "beer/register-beers";
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
