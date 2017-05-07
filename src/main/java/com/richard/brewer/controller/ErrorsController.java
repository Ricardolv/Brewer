package com.richard.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorsController {

	@GetMapping("/404")
	public String pageNotfound() {
		return "404";
	}
}
