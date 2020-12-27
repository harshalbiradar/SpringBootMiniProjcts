package com.harshalit.repository;

import java.io.Serializable;


import org.springframework.data.jpa.repository.JpaRepository;

import com.harshalit.entity.User;


public interface UserRepository extends JpaRepository<User, Serializable> {

	
	public User findByEmailAndPassword(String email, String temPwd);
	
	public User findByEmail(String email);
	
}
