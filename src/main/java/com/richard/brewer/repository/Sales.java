package com.richard.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richard.brewer.model.Sale;

@Repository
public interface Sales extends JpaRepository<Sale, Long> {

}
