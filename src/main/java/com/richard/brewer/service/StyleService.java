package com.richard.brewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richard.brewer.model.Style;
import com.richard.brewer.repository.StyleRepository;

@Service
public class StyleService {
	
	@Autowired
	private StyleRepository styleRepository;

	
	public List<Style> findAll() {
		return styleRepository.findAll();
	}
}
