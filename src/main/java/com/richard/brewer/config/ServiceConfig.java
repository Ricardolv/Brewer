package com.richard.brewer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.richard.brewer.service.BeerService;

@Configuration
@ComponentScan(basePackageClasses = BeerService.class)
public class ServiceConfig {

}
