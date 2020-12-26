package com.harshalit.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table
public class User {

	@Id
	@GeneratedValue
	@Column(name = "UserID")
	private Integer userID;
	
	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "Email")
	private String email;

	@Column(name = "PhoneNumber")
	private String contactNumber;

	@Column(name = "DateOfBirth")
	private Date dob;
	
	@Column(name = "Gender")
	private String gender;
	
	@Column(name = "Country")
	private Integer country;
	
	@Column(name = "State")
	private Integer state;
	
	@Column(name = "City")
	private Integer city;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "AccountStatus")
	private String accountStatus;
	
	
	
	
	
}
