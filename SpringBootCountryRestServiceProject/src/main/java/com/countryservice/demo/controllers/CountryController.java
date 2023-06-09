package com.countryservice.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	
//	@GetMapping("/getcountries")
//	public List getCountries() {
//		return countryService.getAllCountries();
//		
//	}
	
	@GetMapping("/getcountries")
	public List<Country> getCountries() {
		return countryService.getAllCountries();
		
	}
//	
//		// for getCountries/1 
//	@GetMapping("/getcountries/{id}")
//	public Country getCountyById(@PathVariable(value="id") int id) {
//		return countryService.getCountryById(id);
//		
//	}
	
	// for getCountries/1 
	@GetMapping("/getcountries/{id}")
	public ResponseEntity<Country> getCountyById(@PathVariable(value="id") int id) {
		try {  Country country=countryService.getCountryById(id);
				return new ResponseEntity<Country>(country,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
		}
	
	}

	
	
//	// for getCountries/countryName?name=India
//	@GetMapping("/getcountries/countryname")
//	public Country getCountyById(@RequestParam(value="name") String name) {
//		return countryService.getCountryByName(name);
//		
//	}
	
	// for getCountries/countryName?name=India
	@GetMapping("/getcountries/countryname")
	public ResponseEntity<Country> getCountyById(@RequestParam(value="name") String name) {
		try {  
				Country country=countryService.getCountryByName(name);
				return new ResponseEntity<Country>(country,HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
			}
	}
		
	

	@PostMapping("/addcountry")
	public Country addCountry(@RequestBody Country country) {
		return countryService.addCountry(country);
	}
	
//	@PutMapping("/updatecountry")
//	public Country updateCountry(@RequestBody Country country) {
//		return countryService.updateCountry(country);
//	}
//	
	@PutMapping("/updatecountry")
	public ResponseEntity<Country> updateCountry(@PathVariable(value="id")int id,@RequestBody Country country) {
		
		try {
			Country existingCountry=countryService.getCountryById(id);
			existingCountry.setCountryName(country.getCountryName());
			existingCountry.setCountryCapital(country.getCountryCapital());
			Country updatedCountry=countryService.updateCountry(existingCountry);
			return new ResponseEntity<Country>(updatedCountry,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Country>(HttpStatus.CONFLICT);
	}
	}
	
	
	@DeleteMapping("/deletecountry/{id}")
	public AddResponse deleteCountyById(@PathVariable(value="id") int id) {
		return countryService.deleteCountry(id);
		
	}
}
