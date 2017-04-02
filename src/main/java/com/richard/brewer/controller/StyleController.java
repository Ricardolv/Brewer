package com.richard.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.model.Style;

@Controller
@RequestMapping("/styles")
public class StyleController {
	
	@GetMapping("/new")
	public String newStyle(Style stely) {
		return "style/register-styles";
	}
	
	@PostMapping("/new")
	public String register(@Valid Style stely, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return newStyle(stely);
		}
		
		return "redirect:/styles/new";
	}

}
