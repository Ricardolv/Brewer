package com.richard.brewer.model;

import java.io.Serializable;

public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private TypePerson typePerson;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TypePerson getTypePerson() {
		return typePerson;
	}
	public void setTypePerson(TypePerson typePerson) {
		this.typePerson = typePerson;
	}
}
