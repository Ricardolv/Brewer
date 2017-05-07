package com.richard.brewer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.richard.brewer.model.User;
import com.richard.brewer.repository.Users;
import com.richard.brewer.service.exception.UserEmailExistsException;
import com.richard.brewer.service.exception.UserPasswordRequiredException;

@Service
public class UsersService {
	
	@Autowired
	private Users users;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public void save(User user) {
		
		Optional<User> userExist = users.findByEmail(user.getEmail());
		
		if (userExist.isPresent()) {
			throw new UserEmailExistsException("E-mail já cadastrado");
		}
		
		if (user.isNew() && StringUtils.isEmpty(user.getPassword())) {
			throw new UserPasswordRequiredException("Senha é obrigatório para novo usuário");
		}
		
		if (user.isNew()) {
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
			user.setPasswordConfirm(user.getPassword());
		}
		
		users.save(user);
	}

	public List<User> findAll() {
		return users.findAll();
	}

}
