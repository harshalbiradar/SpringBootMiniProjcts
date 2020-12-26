package com.harshalit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "City_Master")
public class City {
	
	@Id
	@GeneratedValue
	@Column(name = "CityId")
	private Integer cityId;
	
	@Column(name = "CityName")
	private String cityName;
	
	@Column(name = "StateId")
	private Integer stateId;
	
	
}
