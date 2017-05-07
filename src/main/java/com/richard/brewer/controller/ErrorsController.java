package com.richard.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorsController {

	@GetMapping("/404")
	public String pageNotfound() {
		return "404";
	}
	
	@RequestMapping("/500")
	public String serverError() {
		return "500";
	}
}
