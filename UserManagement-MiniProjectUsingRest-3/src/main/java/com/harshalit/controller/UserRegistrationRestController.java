package com.harshalit.controller;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	@GetMapping("/getCountry")
	public Map<Integer, String> getCountry() {
		return userService.getCountry();
	}
	
	@GetMapping("/getStates/{countryId}")
	public Map<Integer, String> getStates(@PathVariable Integer countryId) {
		return userService.getState(countryId);
	}
	
	@GetMapping("/getCities/{stateId}")
	public Map<Integer, String> getCities(@PathVariable Integer stateId) {
		return userService.getCity(stateId);
	}
	
	@GetMapping("/emailCheck/{emailId}")
	public String isEmailUnique(@PathVariable String emailId) {
		 if(userService.userIsEmailUnique(emailId)) {
			 return "UNIQUE";
		 }
		return "DUPLICATE";		
	}
	
	// whenever we create the resource the best status to return is 201 CREATED
	@PostMapping("/registerUser")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		User saveUser = userService.registerUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{userName}")
				.buildAndExpand(saveUser.getFirstName()).toUri();
		return ResponseEntity.created(location).build();

	}
	
	/*
	@PostMapping("/registerUser")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		boolean saveUser = userService.registerUser(user);
		if(saveUser==true){
			return new ResponseEntity<>("Registration Successful", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Registration Failed", HttpStatus.BAD_REQUEST);
	}
	 */
	
	
	
	

}
