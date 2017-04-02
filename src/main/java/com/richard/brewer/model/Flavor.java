package com.richard.brewer.model;

public enum Flavor {
	
	SWEETENED("Sweetened"), //Adocicada
	BITTER("Bitter"), //Amarga
	STRONG("Strong"), //Forte
	FRUITY("Fruity"), //Frutada
	SOFT("Soft"); //Suave
	
	private String description;

	private Flavor(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
