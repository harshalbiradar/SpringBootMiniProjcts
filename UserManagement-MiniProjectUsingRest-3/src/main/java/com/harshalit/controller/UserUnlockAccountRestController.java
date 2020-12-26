package com.harshalit.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.harshalit.entity.User;
import com.harshalit.service.UserService;

@RestController
public class UserUnlockAccountRestController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/unlockUser/{emailId}/{newPassword}")
	public ResponseEntity<User> updatePassAndUnlockAcc(@PathVariable String emailId , @PathVariable String newPassword) {
		
		boolean updatePassAndUnlockAcc = userService.updatePasswordAndUnlockAcc(emailId, newPassword);
		if(updatePassAndUnlockAcc==false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	
}
