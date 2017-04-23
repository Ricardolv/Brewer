package com.richard.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richard.brewer.model.Style;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {
	
	Optional<Style> findByNameIgnoreCase(String name);
	
}
