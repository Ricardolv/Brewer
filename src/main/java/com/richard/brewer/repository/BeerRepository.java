package com.richard.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richard.brewer.model.Beer;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
	
}
