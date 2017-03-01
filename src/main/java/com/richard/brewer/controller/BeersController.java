package com.richard.brewer.controller;

import org.springframework.stereotype.Controller;
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
	public String register(Beer beer) {
		
		System.out.println("-> sku " + beer.getSku());
		System.out.println("-> cadastrar");
		
		return "beer/RegisterBeers";
	}

}
