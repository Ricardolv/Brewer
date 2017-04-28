package com.richard.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

public class PhotoStorageRunnable implements Runnable {
	
	private MultipartFile[] files;
	private DeferredResult<String> result;

	public PhotoStorageRunnable(MultipartFile[] files, DeferredResult<String> result) {
		this.files = files;
		this.result = result;
	}

	@Override
	public void run() {
		System.out.println(">>>> files" + files[0].getSize());
		result.setResult("OK");
	}

}
