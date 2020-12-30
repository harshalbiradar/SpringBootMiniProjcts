package com.harshalit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.harshalit.model.UserLogin;
import com.harshalit.service.UserService;

@RestController
public class UserLoginRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/userLogin")
	public String userLoginIn(@RequestBody UserLogin login) {
		return userService.userSignIn(login.getEmailId(), login.getPassword());
	}

}
