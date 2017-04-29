package com.richard.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoStorage {

	public String saveTemporarily(MultipartFile[] files);
}
