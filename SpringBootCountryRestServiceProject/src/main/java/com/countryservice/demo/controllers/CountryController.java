package com.countryservice.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.services.CountryService;

@RestController 
public class CountryController {
	
	//CountryService =new CountryService();
	//This object creation or dependency injection can be done by Autowired and Component Annotations. 
	//Where you want to create object use Autowired with declaration of Object. And for the class whose object is being created should have @Component Annotation
	@Autowired
	CountryService countryService;
	
	
	@GetMapping("/getcountries")
	public List getCountries() {
		return countryService.getAllCountries();
		
	}
	
		// for getCountries/1 
	@GetMapping("/getcountries/{id}")
	public Country getCountyById(@PathVariable(value="id") int id) {
		return countryService.getCountryById(id);
		
	}
	
	// for getCountries/countryName?name=India
	@GetMapping("/getcountries/countryname")
	public Country getCountyById(@RequestParam(value="name") String name) {
		return countryService.getCountryByName(name);
		
	}

	@PostMapping("/addcountry")
	public Country addCountry(@RequestBody Country country) {
		return countryService.addCountry(country);
	}
	
	@PutMapping("/updatecountry")
	public Country updateCountry(@RequestBody Country country) {
		return countryService.updateCountry(country);
	}
	
	
	@DeleteMapping("/deletecountry/{id}")
	public AddResponse deleteCountyById(@PathVariable(value="id") int id) {
		return countryService.deleteCountry(id);
		
	}
}
