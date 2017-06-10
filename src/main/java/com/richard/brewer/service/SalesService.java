package com.richard.brewer.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.richard.brewer.model.Sale;
import com.richard.brewer.model.SaleStatus;
import com.richard.brewer.repository.Sales;
import com.richard.brewer.repository.filter.SaleFilter;

@Service
public class SalesService {

	@Autowired
	private Sales sales;
	
	@Transactional
	public Sale save(Sale sale) {
		
		if (sale.isNew()) {
			sale.setCreationDate(LocalDateTime.now());
		} else {
			Sale saleExist = sales.findOne(sale.getCode());
			sale.setCreationDate(saleExist.getCreationDate());
		}
		
		if (null != sale.getDeliveryDate()) {
			sale.setDeliveryHourDate(LocalDateTime.of(sale.getDeliveryDate(), 
					null != sale.getDeliveryHour() ? sale.getDeliveryHour() : LocalTime.NOON));
		}
		
		return sales.saveAndFlush(sale);
	}
	
	@Transactional
	public Sale issue(Sale sale) {
		sale.setStatus(SaleStatus.ISSUED);
		return save(sale);
	}

	public Page<Sale> filter(SaleFilter saleFilter, Pageable pageable) {
		return sales.filter(saleFilter, pageable);
	}

	public Sale findOfItmes(Long code) {
		return sales.findOfItmes(code);
	}

}
