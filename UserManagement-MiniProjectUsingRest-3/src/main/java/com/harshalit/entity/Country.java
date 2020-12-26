package com.harshalit.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Country_Master")
public class Country {


	@Id
	@GeneratedValue
	@Column(name = "CountryId")
	private Integer countryId;
	
	@Column(name = "CountryName")
	private String countryName="--SelectCountry--";
	
	
	
	
	
}
