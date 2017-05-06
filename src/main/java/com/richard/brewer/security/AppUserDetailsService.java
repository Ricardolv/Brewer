package com.richard.brewer.security;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.richard.brewer.model.User;
import com.richard.brewer.repository.Users;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private Users ussers;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<User> userOptional = ussers.findByEmailAndActive(email);
		
		com.richard.brewer.model.User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos "));
		
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), new HashSet<>());
	}

}
