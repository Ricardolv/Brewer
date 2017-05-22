package com.richard.brewer.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class SalesItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long code;
	private Integer amount;
	private BigDecimal valueUnitary;
	private Beer beer;
	
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public BigDecimal getValueUnitary() {
		return valueUnitary;
	}
	public void setValueUnitary(BigDecimal valueUnitary) {
		this.valueUnitary = valueUnitary;
	}
	public Beer getBeer() {
		return beer;
	}
	public void setBeer(Beer beer) {
		this.beer = beer;
	}
	
    /** 
     * Business 
     */
	
	public BigDecimal getValueTotal() {
		return this.valueUnitary.multiply(new BigDecimal(this.amount));
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesItem other = (SalesItem) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}
