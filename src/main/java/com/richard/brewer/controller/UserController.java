package com.richard.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.model.User;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@GetMapping("/new")
	public String newUser(User user) {
		return "user/register-users";
	}
	
	@PostMapping("/new")
	public String register(@Valid User user, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return newUser(user);
		}
		
		return "redirect:/users/new";
	}

}
