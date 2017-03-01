package com.richard.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.richard.brewer.model.Beer;

@Controller
@RequestMapping("/beers")
public class BeersController {
	
	@GetMapping("/new")
	public String newBeer() {
		return "beer/RegisterBeers";
	}
	
	@PostMapping("/new")
	public String register(@Valid Beer beer, BindingResult result) {
		
		if (result.hasErrors()) {
			
		}
		
		System.out.println("-> sku " + beer.getSku());
		System.out.println("-> cadastrar");
		
		return "beer/RegisterBeers";
	}

}
