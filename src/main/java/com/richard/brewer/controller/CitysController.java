package com.richard.brewer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.model.City;
import com.richard.brewer.repository.Citys;

@Controller
@RequestMapping("/citys")
public class CitysController {
	
	@Autowired
	private Citys citys;
	
	@GetMapping("/new")
	public ModelAndView newCity(City city) {
		ModelAndView mv = new ModelAndView("city/register-citys");
		return mv;
	}
	
	@PostMapping("/new")
	public ModelAndView register(@Valid City city, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return newCity(city);
		}
		
		return new ModelAndView("redirect:/citys/new");
	}
	
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody 
		List<City> searchByStateCode(@RequestParam(name = "state", defaultValue = "-1")Long codeState) {
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) { }
		
		return citys.findByStateCode(codeState);
	}

}
