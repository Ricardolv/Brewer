package com.richard.brewer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.richard.brewer.service.RegisterBeerService;

@Configuration
@ComponentScan(basePackageClasses = RegisterBeerService.class)
public class ServiceConfig {

}
