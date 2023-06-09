package com.countryservice.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.countryservice.demo.beans.Country;

public interface CountryRepository extends JpaRepository<Country,Integer>{

}
