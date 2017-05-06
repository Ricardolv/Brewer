package com.richard.brewer.repository.help.user;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.richard.brewer.model.User;

public class UsersImpl implements UsersQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Optional<User> findByEmailAndActive(String email) {
		return manager
				.createQuery("from User where lower(email) = lower(:email) and active = true ", User.class)
				.setParameter("email", email).getResultList().stream().findFirst();
	}

}
