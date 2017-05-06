package com.richard.brewer.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecutiryController {

	@GetMapping("/login")
	public String login(@AuthenticationPrincipal User user) {
		
		if (null != user) {
			return "redirect:/beers";
		}
		
		return "login";
	}
	
	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}
	
	
	
}
