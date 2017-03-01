package com.richard.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BeersController {
	
	@RequestMapping("/beers/new")
	private String newBeer() {
		return "beer/RegisterBeers";
	}

}
