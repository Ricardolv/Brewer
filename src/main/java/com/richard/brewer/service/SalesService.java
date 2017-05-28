package com.richard.brewer.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richard.brewer.model.Sale;
import com.richard.brewer.model.SalesItem;
import com.richard.brewer.repository.Sales;

@Service
public class SalesService {

	@Autowired
	private Sales sales;
	
	@Transactional
	public void save(Sale sale) {
		
		if (sale.isNew()) {
			sale.setCreateionDate(LocalDateTime.now());
		}
		
		BigDecimal totalValueItems = sale.getItems().stream()
					.map(SalesItem::getTotalValue)
					.reduce(BigDecimal::add)
					.get();
		
		sale.setTotalValue(calculateTotalValue(totalValueItems, sale.getFreightValue(), sale.getDiscountValue()));
		
		if (null != sale.getDeliveryDate()) {
			sale.setDeliveryHourDate(LocalDateTime.of(sale.getDeliveryDate(), sale.getDeliveryHour()));
		}
		
		sales.save(sale);
	}

	private BigDecimal calculateTotalValue(BigDecimal totalValueItems, BigDecimal freightValue, BigDecimal dicountValue) {
		BigDecimal totalValue = totalValueItems
				.add(Optional.ofNullable(freightValue).orElse(BigDecimal.ZERO))
				.subtract(Optional.ofNullable(dicountValue).orElse(BigDecimal.ZERO));
		return totalValue;
	}
}
