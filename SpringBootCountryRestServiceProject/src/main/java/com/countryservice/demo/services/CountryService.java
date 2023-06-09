package com.countryservice.demo.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.controllers.AddResponse;
import com.countryservice.demo.repository.CountryRepository;

@Component
@Service
public class CountryService {
	
	@Autowired
	CountryRepository repository;
	
	static HashMap<Integer,Country> countryIdMap;
	
 
	CountryService(){
		countryIdMap=new HashMap<Integer,Country>();
		
		Country india=new Country(1,"India","Delhi");
		Country usa=new Country(2,"USA","Washinton");
		Country uk=new Country(3,"UK","London");
		
		countryIdMap.put(1, india);
		countryIdMap.put(2, usa);
		countryIdMap.put(3, uk);
	}
	
//	public List getAllCountries() {
//		List countries=new ArrayList(countryIdMap.values());
//		return countries;
//		
//	}
	
	
	public List<Country> getAllCountries() {
      return repository.findAll();
		
	}
	
//	public Country getCountryById(int id) {
//		Country country=countryIdMap.get(id);
//		return country;
//	}
	

	public Country getCountryById(int id) {
		return repository.findById(id).get();
	}
	
	
	
//	public Country getCountryByName(String countryName) {
//		Country country= null;
//		
//		for(int i:countryIdMap.keySet()) {
//			 if(countryIdMap.get(i).equals(countryName))
//				 country=countryIdMap.get(i);
//		}
//		
//		return country;
//	}
//	
	
	public Country getCountryByName(String countryName) {
		Country country= null;
		List<Country> countries=repository.findAll();
		
		for(Country con: countries) {
			if(con.getCountryName().equalsIgnoreCase(countryName)) {
				country=con;
			}
		}
		return country;
	}
	
	
//	
//	public Country addCountry(Country country) {
//		country.setId(getMaxId());
//		countryIdMap.put(country.getId(), country);
//		return country;
//	}
	

	public Country addCountry(Country country) {
		country.setId(getMaxId());
		repository.save(country);
		return country;
	}
	
	
	
	
//	public static int getMaxId() {
//		int max=0;
//		for(int id: countryIdMap.keySet()) {
//			if(max<=id)
//				max=id;
//		}
//		return max+1;
//	}
//	
	public  int getMaxId() {
		System.out.println("Current Size"+repository.findAll().size());
		return repository.findAll().size()+1;
	}
	
	
	
//	public Country updateCountry(Country country) {
//		if(country.getId()>0) {
//			countryIdMap.put(country.getId(), country);
//		}
//		return country;
//	}
	
	public Country updateCountry(Country country) {
	    repository.save(country);
		return country;
	}
	
//	public AddResponse deleteCountry(int id) {
//		countryIdMap.remove(id);
//		AddResponse response=new AddResponse();
//		response.setId(id);
//		response.setMessage("Country with id : "+id+" deleted");
//		return response;
//		
//	}
	
	public AddResponse deleteCountry(int id) {
		repository.deleteById(id);
		AddResponse response=new AddResponse();
		response.setId(id);
		response.setMessage("Country with id : "+id+" deleted");
		return response;
		
	}
}
