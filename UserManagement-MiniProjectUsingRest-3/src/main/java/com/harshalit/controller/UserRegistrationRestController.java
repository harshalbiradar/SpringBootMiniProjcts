package com.harshalit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
		if (userService.userIsEmailUnique(emailId)) {
			return "UNIQUE-EmailId";
		}
		return "DUPLICATE-EmailID";
	}

	// whenever we create the resource the best status to return is 201 CREATED
	@PostMapping("/registerUser")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		if (userService.registerUser(user)) {
			return new ResponseEntity<String>("Registration successful", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Registration failed", HttpStatus.BAD_REQUEST);
	}
	
	
	

}
