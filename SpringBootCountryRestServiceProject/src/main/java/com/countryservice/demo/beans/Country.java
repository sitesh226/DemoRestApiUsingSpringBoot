package com.countryservice.demo.beans;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="CountryDB")
public class Country {
	
	@Id
	@Column(name="ID")
	int id;
	
	@Column(name="CountryName")
	String countryName;
	
	@Column(name="CountryCapital")
	String countryCapital;
	
	
	public Country(int id, String countryName, String countryCapital) {
		super();
		this.id = id;
		this.countryName = countryName;
		this.countryCapital = countryCapital;
	}
	
	public Country() {
		
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCapital() {
		return countryCapital;
	}
	public void setCountryCapital(String countryCapital) {
		this.countryCapital = countryCapital;
	}


}
