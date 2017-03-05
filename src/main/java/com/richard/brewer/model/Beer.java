package com.richard.brewer.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Beer {
	
	@NotBlank(message= "SKU e obrigatorio")
	private String sku;
	
	@NotBlank(message= "Nome e obrigatorio")
	private String name;
	
	@Size(min = 1, max = 50)
	private String description;
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
