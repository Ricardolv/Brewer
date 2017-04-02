package com.richard.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.model.City;

@Controller
@RequestMapping("/citys")
public class CitysController {
	
	@GetMapping("/new")
	public String newCity(City city) {
		return "city/register-citys";
	}
	
	@PostMapping("/new")
	public String register(@Valid City city, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return newCity(city);
		}
		
		return "redirect:/citys/new";
	}

}
