package com.richard.brewer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.richard.brewer.dto.PhotosDTO;
import com.richard.brewer.storage.PhotoStorageRunnable;

@RestController
@RequestMapping("/photos")
public class PhotosControler {
	
	@PostMapping
	public DeferredResult<PhotosDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		
		DeferredResult<PhotosDTO> result = new DeferredResult<>();
		
		Thread thread = new Thread(new PhotoStorageRunnable(files, result));
		thread.start();
		
		
		return result;
	}

}
