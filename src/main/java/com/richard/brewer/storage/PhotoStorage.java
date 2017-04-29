package com.richard.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoStorage {

	public void saveTemporarily(MultipartFile[] files);
}
