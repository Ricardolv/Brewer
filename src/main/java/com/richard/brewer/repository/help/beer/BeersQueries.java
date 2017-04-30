package com.richard.brewer.repository.help.beer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.richard.brewer.model.Beer;
import com.richard.brewer.repository.filter.BeerFilter;

public interface BeersQueries {
	
	public Page<Beer> beerFilter(BeerFilter beerFilter, Pageable pageable);

}
