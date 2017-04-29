package com.richard.brewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.richard.brewer.service.BeerService;
import com.richard.brewer.storage.PhotoStorage;
import com.richard.brewer.storage.local.PhotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = BeerService.class)
public class ServiceConfig {
	
	@Bean
	public PhotoStorage photoStorage() {
		return new PhotoStorageLocal();
	}

}
