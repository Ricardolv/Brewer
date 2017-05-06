package com.richard.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.model.User;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@GetMapping("/new")
	public ModelAndView newUser(User user) {
		ModelAndView mv = new ModelAndView("user/register-users");
		return mv;
	}
	
	@PostMapping("/new")
	public ModelAndView save(@Valid User user, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return newUser(user);
		}
		
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
		return new ModelAndView("redirect:/users/new");
	}

}
