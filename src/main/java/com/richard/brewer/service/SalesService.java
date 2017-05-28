package com.richard.brewer.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richard.brewer.model.Sale;
import com.richard.brewer.model.SaleStatus;
import com.richard.brewer.repository.Sales;

@Service
public class SalesService {

	@Autowired
	private Sales sales;
	
	@Transactional
	public void save(Sale sale) {
		
		if (sale.isNew()) {
			sale.setCreationDate(LocalDateTime.now());
		}
		
		if (null != sale.getDeliveryDate()) {
			sale.setDeliveryHourDate(LocalDateTime.of(sale.getDeliveryDate(), 
					null != sale.getDeliveryHour() ? sale.getDeliveryHour() : LocalTime.NOON));
		}
		
		sales.save(sale);
	}
	
	@Transactional
	public void issue(Sale sale) {
		sale.setStatus(SaleStatus.ISSUED);
		save(sale);
	}

}
