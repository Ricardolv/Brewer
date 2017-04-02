package com.richard.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.richard.brewer.model.Style;

public interface StyleRepository extends JpaRepository<Style, Long> {
	
}
