package com.richard.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.richard.brewer.model.User;
import com.richard.brewer.repository.Users;
import com.richard.brewer.service.exception.BusinessRuleException;

@Service
public class UsersService {
	
	@Autowired
	private Users users;

	@Transactional
	public void save(User user) {
		
		Optional<User> userExist = users.findByEmail(user.getEmail());
		
		if (userExist.isPresent()) {
			throw new BusinessRuleException("E-mail já cadastrado");
		}
		
		users.save(user);
	}

}
