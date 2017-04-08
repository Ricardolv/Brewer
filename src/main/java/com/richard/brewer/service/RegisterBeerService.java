package com.richard.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richard.brewer.model.Beer;
import com.richard.brewer.repository.BeerRepository;

@Service
public class RegisterBeerService {
	
	@Autowired
	private BeerRepository beerRepository;
	
	public void save(Beer beer) {
		beerRepository.save(beer);
	}
}
