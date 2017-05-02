package com.richard.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.richard.brewer.model.Client;
import com.richard.brewer.repository.Clients;
import com.richard.brewer.service.exception.BusinessRuleException;

@Service
public class ClientsService {
	
	@Autowired
	private Clients clients;
	
	@Transactional
	public void save(Client client) {
		
		Optional<Client> clientExist = clients.findByCpfCnpj(client.getCpfCnpjWithoutFormatting());
		
		if (clientExist.isPresent()) {
			throw new BusinessRuleException("CPF/CNPJ já cadastrado");
		}
		
		clients.save(client);
	}

}
