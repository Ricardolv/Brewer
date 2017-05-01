package com.richard.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.richard.brewer.model.Client;
import com.richard.brewer.repository.Clients;

@Service
public class ClientsService {
	
	@Autowired
	private Clients clients;
	
	@Transactional
	public void save(Client client) {
		
		
		
		clients.save(client);
	}

}
