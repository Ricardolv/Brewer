package com.richard.brewer.session;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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

		Optional<SalesItem> salesItemOptional = searchItemByBeer(beer);
		
		SalesItem salesItem = null;
		if (salesItemOptional.isPresent()) {
			salesItem = salesItemOptional.get();
			salesItem.setAmount(salesItem.getAmount() + amount);
			
		} else {
			
			salesItem = new SalesItem();
			salesItem.setBeer(beer);
			salesItem.setValueUnitary(beer.getValue());
			salesItem.setAmount(amount);
			items.add(0, salesItem);
		}

	}
	
	public void alterAmountItems(Beer beer, Integer amount) {
		SalesItem item = searchItemByBeer(beer).get();
		item.setAmount(amount);
	}
	
	public void deleteItem(Beer beer) {
		int index = IntStream.range(0, items.size())
				.filter(i -> items.get(i).getBeer().equals(beer))
				.findAny().getAsInt();
		items.remove(index);
	}

	public int total() {
		return items.size();
	}

	public List<SalesItem> getItems() {
		return items;
	}
	
	private Optional<SalesItem> searchItemByBeer(Beer beer) {
		return items.stream()
					.filter(i -> i.getBeer().equals(beer))
					.findAny();
	}
}
