package com.harshalit.controller;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.harshalit.entity.User;
import com.harshalit.service.UserService;

@RestController
public class UserRegistrationRestController {

	@Autowired
	private UserService userService;

	
	// whenever we create the resource the best status to return is 201 CREATED
	@PostMapping("/registerUser")
	public ResponseEntity<Object> registerUser(@RequestBody User user) {
		User savedUser = userService.registerUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{userName}")
				.buildAndExpand(savedUser.getFirstName()).toUri();
		return ResponseEntity.created(location).build();

	}
	
	

}
