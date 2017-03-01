package com.richard.brewer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.richard.brewer.controller.BeersController;

@Configuration
@ComponentScan(basePackageClasses = { BeersController.class})
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

}
