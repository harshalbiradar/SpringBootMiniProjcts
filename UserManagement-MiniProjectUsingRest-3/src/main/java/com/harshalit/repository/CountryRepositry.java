package com.harshalit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshalit.entity.Country;

public interface CountryRepositry extends JpaRepository<Country, Serializable> {

}
