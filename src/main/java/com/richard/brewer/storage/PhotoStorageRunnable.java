package com.richard.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.richard.brewer.dto.PhotosDTO;

public class PhotoStorageRunnable implements Runnable {
	
	private MultipartFile[] files;
	private DeferredResult<PhotosDTO> result;

	public PhotoStorageRunnable(MultipartFile[] files, DeferredResult<PhotosDTO> result) {
		this.files = files;
		this.result = result;
	}

	@Override
	public void run() {
		System.out.println(">>>> files" + files[0].getSize());
		
		String name = files[0].getOriginalFilename();
		String contentType = files[0].getContentType();
		
		result.setResult(new PhotosDTO(name, contentType));
	}

}
