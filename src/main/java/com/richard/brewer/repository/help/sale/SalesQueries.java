package com.richard.brewer.repository.help.sale;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.richard.brewer.model.Sale;
import com.richard.brewer.repository.filter.SaleFilter;

public interface SalesQueries {
	
	public Page<Sale> filter(SaleFilter filtro, Pageable pageable);
}
