package com.richard.brewer.repository.help.beer;

import java.util.List;

import com.richard.brewer.model.Beer;
import com.richard.brewer.repository.filter.BeerFilter;

public interface BeersQueries {
	
	public List<Beer> beerFilter(BeerFilter beerFilter);

}
