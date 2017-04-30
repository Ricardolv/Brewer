package com.richard.brewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.richard.brewer.model.Beer;
import com.richard.brewer.repository.Beers;
import com.richard.brewer.repository.filter.BeerFilter;
import com.richard.brewer.service.event.beer.BeerSaveEvent;

@Service
public class BeerService {
	
	@Autowired
	private Beers beers;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void save(Beer beer) {
		beers.save(beer);
		
		publisher.publishEvent(new BeerSaveEvent(beer));
	}

	public List<Beer> findAll() {
		return beers.findAll();
	}
	
	public Page<Beer> filter(BeerFilter beerFilter, Pageable pageable) {
		return beers.filter(beerFilter, pageable);
	}
}
