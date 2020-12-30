package com.harshalit.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harshalit.entity.City;
import com.harshalit.entity.Country;
import com.harshalit.entity.State;
import com.harshalit.entity.User;
import com.harshalit.repository.CityRepository;
import com.harshalit.repository.CountryRepositry;
import com.harshalit.repository.StateRepository;
import com.harshalit.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepositry;

	@Autowired
	private CountryRepositry countryRepositry;

	@Autowired
	private StateRepository stateRepositry;

	@Autowired
	private CityRepository cityRepositry;

	@Autowired
	private EmailService emailService;

	// Registration page operations
	@Override
	public Map<Integer, String> getCountry() {
		List<Country> countryList = countryRepositry.findAll();
		Map<Integer, String> countryMap = new HashMap<>();
		countryList.forEach(country -> {
			countryMap.put(country.getCountryId(), country.getCountryName());
		});
		System.out.println("countryMap: " + countryMap);
		return countryMap;

	}

	@Override
	public Map<Integer, String> getState(Integer countryId) {
		List<State> stateList = stateRepositry.findByCountryId(countryId);
		Map<Integer, String> stateMap = new HashMap<>();
		stateList.forEach(state -> {
			stateMap.put(state.getStateId(), state.getStateName());
		});
		System.out.println("stateMap: " + stateMap);
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCity(Integer stateId) {
		List<City> cityList = cityRepositry.findByStateId(stateId);
		Map<Integer, String> cityMap = new HashMap<>();
		cityList.forEach(city -> {
			cityMap.put(city.getCityId(), city.getCityName());
		});
		System.out.println("cityMap: " + cityMap);
		return cityMap;
	}

	@Override
	public boolean registerUser(User user) {
		user.setPassword(passwordGenerator());
		user.setAccountStatus("LOCKED");
		User savedUser = userRepositry.save(user);
		String emailBody = getUnlockAccountEmailBody(savedUser);
		String subject = "UNLOCK YOUR ACCOUNT | IES";
		boolean isSent = emailService.sendMail(subject, emailBody, user.getEmail());

		return savedUser.getUserID() != null && isSent;
	}

	private String passwordGenerator() {
		char[] password = new char[6];
		String alphaNumeric = "ABCDEFGHabcdefgh1234567890";
		Random randomPw = new Random();
		for (int i = 0; i <= 5; i++) {
			password[i] = alphaNumeric.charAt(randomPw.nextInt(alphaNumeric.length()));
		}
		System.out.println("Random password: " + password.toString());
		return password.toString();
	}

	@Override
	public boolean userIsEmailUnique(String email) {
		User isEmailUnique = userRepositry.findByEmail(email);
		if (isEmailUnique == null) {
			return true;
		}
		return false;
	}

	// login page operations

	@Override
	public String userSignIn(String email, String password) {

		User userDetails = userRepositry.findByEmailAndPassword(email, password);
		if (userDetails != null) {
			if (userDetails.getAccountStatus().equals("LOCKED")) {
				return "ACCOUNT_LOCKED";
			} else {
				return "LOGIN SUCCESS";
			}
		}
		return "INVALID_CREDENTIALS";

	}

	@Override
	public boolean checkTemporaryPassIsValid(String email, String temPwd) {
		User userDetails = userRepositry.findByEmailAndPassword(email, temPwd);
		return userDetails.getUserID() != null;
	}

	@Override
	public boolean updatePasswordAndUnlockAcc(String email, String newPassword) {
		User userDetails = userRepositry.findByEmail(email);
		userDetails.setPassword(newPassword);
		userDetails.setAccountStatus("UNLOCKED");
		try {
			userRepositry.save(userDetails);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	

/*	
	private String getUnlockAccountEmailBody(User user) {

		StringBuilder sb = new StringBuilder("");
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt")));

			String line = br.readLine();

			while (line != null) {
				if (line.contains("{FNAME}")) {
					line = line.replace("{FNAME}", user.getFirstName());
				}
				if (line.contains("{LNAME}")) {
					line = line.replace("{LNAME}", user.getLastName());
				}
				if (line.contains("{TEMP-PWD}")) {
					line = line.replace("{TEMP-PWD}", user.getPassword());
				}
				if (line.contains("{EMAIL}")) {
					line = line.replace("{EMAIL}", user.getEmail());
				}
				sb.append(line);
				line = br.readLine();
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();

	}
*/
	
	public String getUnlockAccountEmailBody(User user) {
		String fileName = "UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt";
		List<String> replacedLines = null;
		String mailBody = null;
		try {
			Path path = Paths.get(fileName, "");
			Stream<String> lines = Files.lines(path);
			replacedLines = lines.map(line ->line.replace("{FNAME}",
										     user.getFirstName())
										     .replace("{LNAME}", user.getLastName())
							                 .replace("{TEMP-PWD}", user.getPassword())
							                 .replace("{EMAIL}", user.getEmail()))
					             	   	  .collect(Collectors.toList());
			mailBody = String.join("", replacedLines);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}

	@Override
	public String forgotPassword(String email) {
		User userDetails = userRepositry.findByEmail(email);
		if(userDetails!=null) {
			String emailBody = forgotPasswordEmailBody(userDetails);
			String subject = "FORGOT YOUR PASSWORD | IES";
			boolean isSent = emailService.sendMail(subject, emailBody, userDetails.getEmail());
			if(isSent == true) {
				return "Forgot Password email sent successfully";
			}else {
				return "Fail to send forgot Password email";
			}
		}
		return "Your email is not registered";
	}
	
	public String forgotPasswordEmailBody(User user) {
		
		String filename = "FORGOT-PASS-EMAIL-BODY-TEMPLATE.txt";
		List<String> replacedLines = null;
		String mailBody = null;
		try {
			Path path = Paths.get(filename, "");
			Stream<String> lines = Files.lines(path);
			replacedLines = lines.map(line ->line.replace("{FNAME}",user.getFirstName())
										         .replace("{LNAME}", user.getLastName())
							                     .replace("{FORGOT-PWD}", user.getPassword()))
					             	   	         .collect(Collectors.toList());
			mailBody = String.join("", replacedLines);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}
	
	
	
	
}
