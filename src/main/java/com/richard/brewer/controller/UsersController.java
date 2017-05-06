package com.richard.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.richard.brewer.model.User;
import com.richard.brewer.service.GroupsService;
import com.richard.brewer.service.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private GroupsService groupsServie;
	
	@GetMapping("/new")
	public ModelAndView newUser(User user) {
		ModelAndView mv = new ModelAndView("user/register-users");
		mv.addObject("groups", groupsServie.findAll());
		return mv;
	}
	
	@PostMapping("/new")
	public ModelAndView save(@Valid User user, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return newUser(user);
		}
		
		usersService.save(user);
		attributes.addFlashAttribute("message", "Usuário salvo com sucesso");
		return new ModelAndView("redirect:/users/new");
	}

}
