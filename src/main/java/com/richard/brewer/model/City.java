package com.richard.brewer.model;

import java.io.Serializable;

public class City implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
