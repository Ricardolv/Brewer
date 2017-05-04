package com.richard.brewer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.richard.brewer.model.City;
import com.richard.brewer.repository.Citys;
import com.richard.brewer.service.exception.BusinessRuleException;

@Service
public class CitysService {
	
	@Autowired
	private Citys citys;
	
	
	public List<City> findByStateCode(Long codeState) {
		
		return citys.findByStateCode(codeState);
	}

	@Transactional
	public void save(City city) {
		
		Optional<City> cityExist = citys.findByStateAndName(city.getState(), city.getName());
		
		if (cityExist.isPresent()) {
			throw new BusinessRuleException("Nome da cidade já cadastrado");
		}
		
		citys.save(city);
	}
	
	

}
