package com.richard.brewer.storage.local;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.richard.brewer.storage.PhotoStorage;

public class PhotoStorageLocal implements PhotoStorage {
	
	private static final Logger logger = LoggerFactory.getLogger(PhotoStorageLocal.class);
	
	private Path local;
	private Path localTemp;
	

	public PhotoStorageLocal() {
		this.local = FileSystems.getDefault().getPath(System.getenv("HOME"), ".brewerphotos");
		createPast();
	}
	
	public PhotoStorageLocal(Path path) {
		this.local = path;
		createPast();
	}


	private void createPast() {
		try {
			Files.createDirectories(this.local);
			this.localTemp = FileSystems.getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemp);
			
			if (logger.isDebugEnabled()) {
				logger.debug("Pasta criadas para salvar fotos.");
				logger.debug("Pasta default: " + this.local.toAbsolutePath());
				logger.debug("Pasta temporaria: " + this.localTemp.toAbsolutePath());
			}
			
		} catch (IOException e) {
			throw new RuntimeException("Error create past for save image", e);
		}
		
	}

	@Override
	public String saveTemporarily(MultipartFile[] files) {
		String newName = null;
		
		if (files != null && files.length > 0) {
			MultipartFile multipartFile = files[0];
			newName = fileRename(multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(new File(this.localTemp.toAbsolutePath().toString() + FileSystems.getDefault().getSeparator() + newName));
			} catch (IOException e) {
				throw new RuntimeException("Erro salvando a foto na pasta temporária", e);
			}
		}
		
		return newName;
	}

	private String fileRename(String originalFilename) {
		
		String newName = UUID.randomUUID().toString() + "_" + originalFilename;
		
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Nome original: %s, novo nome: %s", originalFilename, newName));
		}
		return newName;
	}
	
	

}
