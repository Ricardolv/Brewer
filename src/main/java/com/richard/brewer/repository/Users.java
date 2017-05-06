package com.richard.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richard.brewer.model.User;

@Repository
public interface Users extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

}
