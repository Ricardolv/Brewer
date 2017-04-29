package com.richard.brewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.richard.brewer.model.Beer;
import com.richard.brewer.repository.BeerRepository;
import com.richard.brewer.service.event.beer.BeerSaveEvent;

@Service
public class BeerService {
	
	@Autowired
	private BeerRepository beerRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void save(Beer beer) {
		beerRepository.save(beer);
		
		publisher.publishEvent(new BeerSaveEvent(beer));
	}

	public List<Beer> findAll() {
		return beerRepository.findAll();
	}
}
