package com.richard.brewer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.richard.brewer.model.Style;
import com.richard.brewer.repository.Styles;
import com.richard.brewer.repository.filter.StyleFilter;
import com.richard.brewer.service.exception.NameExistsException;

@Service
public class StyleService {
	
	@Autowired
	private Styles styles;
	
	public List<Style> findAll() {
		return styles.findAll();
	}

	@Transactional
	public Style save(Style style) {
		
		Optional<Style> styleExist = styles.findByNameIgnoreCase(style.getName());
		
		if (styleExist.isPresent()) {
			throw new NameExistsException("Nome do estilo já cadastrado");
		}	
		
		return styles.saveAndFlush(style);
	}
	
	public Page<Style> filter(StyleFilter styleFilter, Pageable pageable) {
		return styles.filter(styleFilter, pageable);
	}
}
