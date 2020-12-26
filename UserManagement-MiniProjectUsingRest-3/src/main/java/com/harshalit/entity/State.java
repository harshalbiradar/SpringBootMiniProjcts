package com.harshalit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "State_Master")
public class State {
	

	@Id
	@GeneratedValue
	@Column(name = "StateId")
	private Integer stateId;
	
	@Column(name = "StateName")
	private String stateName;
	
	@Column(name = "CountryId")
	private Integer countryId;
	
	
}
