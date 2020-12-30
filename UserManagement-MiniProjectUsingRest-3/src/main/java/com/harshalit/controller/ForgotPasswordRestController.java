package com.harshalit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshalit.service.UserService;

@RestController
public class ForgotPasswordRestController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/forgotPass/{emailId}")
	public String forgotUserPwd(@PathVariable String emailId) {
		return userService.forgotPassword(emailId);
	}
	 
	
	
}
