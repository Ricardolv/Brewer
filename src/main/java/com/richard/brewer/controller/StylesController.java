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

import com.richard.brewer.model.Style;
import com.richard.brewer.service.StyleService;
import com.richard.brewer.service.exception.BusinessRuleException;

@Controller
@RequestMapping("/styles")
public class StylesController {
	
	private static final Logger logger = LoggerFactory.getLogger(StylesController.class);
	
	@Autowired
	private StyleService styleService;
	
	@GetMapping("/new")
	public ModelAndView newStyle(Style stely) {
		ModelAndView mv = new ModelAndView("style/register-styles");
		return mv;
	}
	
	@PostMapping("/new")
	public ModelAndView register(@Valid Style stely, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors())
			return newStyle(stely);
		
		try {
			styleService.save(stely);
		} catch (BusinessRuleException s) {
			result.rejectValue("name", s.getMessage(), s.getMessage());
			return newStyle(stely);
		}
		
		attributes.addFlashAttribute("message", "Estilo salvo com sucesso!");
		
		return new ModelAndView("redirect:/styles/new");
	}

}
