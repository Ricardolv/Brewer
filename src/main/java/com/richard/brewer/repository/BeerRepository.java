package com.richard.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richard.brewer.model.Beer;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
	
	public Optional<Beer> findBySkuIgnoreCase(String sku);
	
}
