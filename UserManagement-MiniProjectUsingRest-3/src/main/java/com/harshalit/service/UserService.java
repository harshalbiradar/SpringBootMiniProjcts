package com.harshalit.service;

import java.util.Map;

import com.harshalit.entity.User;

public interface UserService {

	//Registration  page operations
	public Map<Integer, String> getCountry();


	public Map<Integer, String> getState(Integer countryId);

	public Map<Integer, String> getCity(Integer stateId);
	
	public User registerUser(User user);

	public boolean userIsEmailUnique(String email);
	
	
	//login page operations
	public String userSignIn(String email, String password);

	
	/* public boolean userAccountStatus(String email); */
	
	//unlock account operations
	public boolean checkTemporaryPassIsValid(String email, String tempPwd);

	
	public boolean updatePasswordAndUnlockAcc(String email, String newPassword);
	
	public String forgotPassword(String email);

}
