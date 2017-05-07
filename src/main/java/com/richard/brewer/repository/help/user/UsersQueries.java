package com.richard.brewer.repository.help.user;

import java.util.List;
import java.util.Optional;

import com.richard.brewer.model.User;
import com.richard.brewer.repository.filter.UserFilter;

public interface UsersQueries {
	
	public Optional<User> findByEmailAndActive(String email);
	
	public List<String> permissions(User user);
	
	public List<User> filter(UserFilter userFilter);
}
