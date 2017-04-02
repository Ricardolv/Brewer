package com.richard.brewer.model;

public enum Origin {
	
	NATIONAL("National"),
	INTERNATIONAL("International");
	
	private String decription;

	private Origin(String decription) {
		this.decription = decription;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}
}
