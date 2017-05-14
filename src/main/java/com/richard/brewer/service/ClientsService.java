package com.richard.brewer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.richard.brewer.model.Client;
import com.richard.brewer.repository.Clients;
import com.richard.brewer.repository.filter.ClientFilter;
import com.richard.brewer.service.exception.ClientCpfCnpjExistsException;

@Service
public class ClientsService {
	
	@Autowired
	private Clients clients;
	
	@Transactional
	public void save(Client client) {
		
		Optional<Client> clientExist = clients.findByCpfCnpj(client.getCpfCnpjWithoutFormatting());
		
		if (clientExist.isPresent()) {
			throw new ClientCpfCnpjExistsException("CPF/CNPJ já cadastrado");
		}
		
		clients.save(client);
	}

	public Page<Client> filter(ClientFilter clientFilter, Pageable pageable) {
		return clients.filter(clientFilter, pageable);
	}

	public List<Client> findByNameStartingWithIgnoreCase(String name) {
		return clients.findByNameStartingWithIgnoreCase(name);
	}

}
