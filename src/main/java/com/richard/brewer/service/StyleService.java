package com.richard.brewer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.richard.brewer.model.Style;
import com.richard.brewer.repository.StyleRepository;
import com.richard.brewer.service.exception.BusinessRuleException;

@Service
public class StyleService {
	
	@Autowired
	private StyleRepository styleRepository;
	
	public List<Style> findAll() {
		return styleRepository.findAll();
	}

	@Transactional
	public void save(Style style) {
		
		Optional<Style> styleOptional = styleRepository.findByNameIgnoreCase(style.getName());
		if (styleOptional.isPresent())
			throw new BusinessRuleException("Nome do estilo ja cadastrado");
		
		styleRepository.save(style);
	}
}
