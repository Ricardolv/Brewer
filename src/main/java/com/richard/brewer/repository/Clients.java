package com.richard.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richard.brewer.model.Client;

@Repository
public interface Clients extends JpaRepository<Client, Long> {

}
