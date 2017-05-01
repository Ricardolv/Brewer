package com.richard.brewer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richard.brewer.model.City;

@Repository
public interface Citys extends JpaRepository<City, Long> {
	
	public List<City> findByStateCode(Long codeState);

}
