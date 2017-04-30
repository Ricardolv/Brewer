package com.richard.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.controller.page.PageWrapper;
import com.richard.brewer.model.Style;
import com.richard.brewer.repository.filter.StyleFilter;
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
	public ModelAndView register(@Valid Style style, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors())
			return newStyle(style);
		
		try {
			styleService.save(style);
		} catch (BusinessRuleException e) {
			result.rejectValue("name", e.getMessage(), e.getMessage());
			return newStyle(style);
		}
		
		attributes.addFlashAttribute("message", "Estilo salvo com sucesso!");
		
		return new ModelAndView("redirect:/styles/new");
	}
	
	@PostMapping(value = "/modal", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> modalStyleSave(@RequestBody @Valid Style style, BindingResult result) {
		
		if (result.hasErrors())
			return ResponseEntity.badRequest().body(result.getFieldError("name").getDefaultMessage());
		
		style = styleService.save(style);
		
		return ResponseEntity.ok(style);
	}
	
	@GetMapping
	public ModelAndView search(StyleFilter styleFilter, BindingResult result, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("style/search-styles");
		
		PageWrapper<Style> pageWrapper = new PageWrapper<>(styleService.filter(styleFilter, pageable), httpServletRequest);
		mv.addObject("page", pageWrapper);
		
		return mv;
	}

}
