package com.richard.brewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.richard.brewer.model.Beer;
import com.richard.brewer.repository.BeerRepository;

@Service
public class BeerService {
	
	@Autowired
	private BeerRepository beerRepository;
	
	@Transactional
	public void save(Beer beer) {
		beerRepository.save(beer);
	}

	public List<Beer> findAll() {
		return beerRepository.findAll();
	}
}
