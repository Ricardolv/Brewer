package com.richard.brewer.repository.help.user;

import java.util.Optional;

import com.richard.brewer.model.User;

public interface UsersQueries {
	
	public Optional<User> findByEmailAndActive(String email);
}
