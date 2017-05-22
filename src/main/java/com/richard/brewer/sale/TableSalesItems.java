package com.richard.brewer.sale;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.richard.brewer.model.SalesItem;

public class TableSalesItems implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<SalesItem> items = new ArrayList<>();
	
	public BigDecimal getValueTotal() {
		return items.stream()
				.map(SalesItem::getValueTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
}
