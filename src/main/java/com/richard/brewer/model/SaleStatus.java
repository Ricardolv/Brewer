package com.richard.brewer.model;

public enum SaleStatus {
	
	BUDGET("Orçamento"), //Orçamento
	ISSUED("Emitida"), //Emitida
	CANCELED("Cancelada"); //Cancelada

	private String descriotion;

	SaleStatus(String descriotion) {
		this.descriotion = descriotion;
	}

	public String getDescriotion() {
		return descriotion;
	}

}
