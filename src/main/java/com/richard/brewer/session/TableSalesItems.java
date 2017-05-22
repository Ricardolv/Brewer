package com.richard.brewer.session;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.richard.brewer.model.Beer;
import com.richard.brewer.model.SalesItem;

/**
 * Anotacao @SessionScope que insere este componente na sessao para cada usuario logado
 * @author richard
 */
@SessionScope 
@Component
public class TableSalesItems implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<SalesItem> items = new ArrayList<>();
	
	public BigDecimal getValueTotal() {
		return items.stream()
				.map(SalesItem::getValueTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void addItem(Beer beer, Integer amount) {
		SalesItem salesItem = new SalesItem();
		salesItem.setBeer(beer);
		salesItem.setValueUnitary(beer.getValue());
		salesItem.setAmount(amount);
		
		items.add(salesItem);
	}
	
	public int total() {
		return items.size();
	}

	public Object getItems() {
		return items;
	}
}
