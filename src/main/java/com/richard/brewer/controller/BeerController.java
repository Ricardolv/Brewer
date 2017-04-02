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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.model.Beer;
import com.richard.brewer.model.Flavor;
import com.richard.brewer.model.Origin;
import com.richard.brewer.repository.BeerRepository;
import com.richard.brewer.repository.StyleRepository;

@Controller
@RequestMapping("/beers")
public class BeerController {
	
	private static final Logger logger = LoggerFactory.getLogger(BeerController.class);
	
	@Autowired
	private BeerRepository beerRepository;
	
	@Autowired
	private StyleRepository styleRepository;
	
	@GetMapping("/new")
	public ModelAndView newBeer(Beer beer) {
		ModelAndView mv = new ModelAndView("beer/register-beers");
		mv.addObject("flavors", Flavor.values());
		mv.addObject("styles", styleRepository.findAll());
		mv.addObject("origins", Origin.values());
		return mv;
	}
	
	@PostMapping("/new")
	public ModelAndView register(@Valid Beer beer, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return newBeer(beer);
		}
		
		attributes.addFlashAttribute("message", "Cerveja salva com sucesso!");
		
		return new ModelAndView("redirect:/beers/new");
	}

}
