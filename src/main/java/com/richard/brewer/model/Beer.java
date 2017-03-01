package com.richard.brewer.model;

import org.hibernate.validator.constraints.NotBlank;

public class Beer {
	
	@NotBlank
	private String sku;
	
	@NotBlank
	private String name;
	
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
}
