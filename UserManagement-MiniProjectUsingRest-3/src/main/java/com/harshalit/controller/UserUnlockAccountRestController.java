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
	public ResponseEntity<String> updatePassAndUnlockAcc(@PathVariable String emailId, @PathVariable String newPassword) {

		boolean updatePassAndUnlockAcc = userService.updatePasswordAndUnlockAcc(emailId, newPassword);
		if (updatePassAndUnlockAcc == true) {
			return new ResponseEntity<>("Password updated successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("Password failed to update",HttpStatus.BAD_REQUEST);

	}

	@PostMapping("/tempPassValid/{emailId}/{tempPwd}")
	public ResponseEntity<String> isTmpPwdValid(@PathVariable String emailId , @PathVariable String tempPwd) {
		boolean checkTemporaryPassIsValid = 
				userService.checkTemporaryPassIsValid(emailId, tempPwd);
		if(checkTemporaryPassIsValid == true) {
			return new ResponseEntity<>("Temporary password is valid",HttpStatus.OK);
		}
		return new ResponseEntity<>("Temporary password is invalid",HttpStatus.BAD_REQUEST);
	}
}
