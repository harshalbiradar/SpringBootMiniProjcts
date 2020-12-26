package com.harshalit.repository;

import java.io.Serializable;


import org.springframework.data.jpa.repository.JpaRepository;

import com.harshalit.entity.User;


public interface UserRepository extends JpaRepository<User, Serializable> {

	public User findByEmail(String email);
	
	public User findByEmailAndPassword(String email, String password);
	
	 
	
}
