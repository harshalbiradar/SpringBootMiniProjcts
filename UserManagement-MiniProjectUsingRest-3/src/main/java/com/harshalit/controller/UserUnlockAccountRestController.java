package com.harshalit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harshalit.model.UnlockAccount;
import com.harshalit.service.UserService;

@RestController
public class UserUnlockAccountRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/unlockUserAcc")
	public ResponseEntity<String> updatePassAndUnlockAcc(@RequestBody UnlockAccount unlockAcc) {

		boolean checkTemporaryPassIsValid = 
				userService.checkTemporaryPassIsValid(unlockAcc.getEmailId(), unlockAcc.getTempPwd());
		if(checkTemporaryPassIsValid == true) {
			if(unlockAcc.getNewPwd().equals(unlockAcc.getConfirmPwd())) {
				boolean updatePassAndUnlockAcc = userService.updatePasswordAndUnlockAcc(unlockAcc.getEmailId(), unlockAcc.getNewPwd());
				return new ResponseEntity<>("Your Account is successfully unlocked please go to the login",HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Please enter correct password",HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>("Temporary password is invalid",HttpStatus.BAD_REQUEST);
	}
		
	
}
