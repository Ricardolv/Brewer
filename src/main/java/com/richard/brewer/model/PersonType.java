package com.richard.brewer.model;

public enum PersonType {
	
	PHYSICAL("Fisica", "CPF", "000.000.000-00"),
	JURIDICAL("Juridica", "CPNJ", "00.000.000/0000-00");
	
	private String description;
	private String document;
	private String mask;
	
	PersonType(String description, String document, String mask ) {
		this.description = description;
		this.document = document;
		this.mask = mask;
	}

	public String getDescription() {
		return description;
	}

	public String getDocument() {
		return document;
	}

	public String getMask() {
		return mask;
	}

}
