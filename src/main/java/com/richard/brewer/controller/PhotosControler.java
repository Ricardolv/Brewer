package com.richard.brewer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photos")
public class PhotosControler {
	
	@PostMapping
	public String upload(@RequestParam("files[]") MultipartFile[] files) {
		
		
		
		return "ok";
	}

}
