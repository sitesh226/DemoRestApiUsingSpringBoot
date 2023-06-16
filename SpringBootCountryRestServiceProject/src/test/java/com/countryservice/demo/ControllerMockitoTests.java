package com.countryservice.demo;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.controllers.CountryController;
import com.countryservice.demo.services.CountryService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;



import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {ControllerMockitoTests.class})
public class ControllerMockitoTests {
	
	@Mock
	CountryService countryService;
	
	@InjectMocks
	CountryController countryController;
	
	List<Country> mockedCountries;
	Country country;
	
	@Test
	@Order(1)
	public void test_getAllCountries() {
		
		mockedCountries=new ArrayList<Country>();
		mockedCountries.add(new Country(1,"India","Delhi"));
		mockedCountries.add(new Country(2,"Japan","Tokyo"));
		
		
		when(countryService.getAllCountries()).thenReturn(mockedCountries);
		ResponseEntity<List<Country>> response=countryController.getCountries();
		
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertEquals(2,response.getBody().size());
	}
	
	@Test
	@Order(2)
	public void test_getCountryById() {
		
		country=new Country(1,"India","Delhi");
		int countryId=1;
		
		
		when(countryService.getCountryById(countryId)).thenReturn(country);
		ResponseEntity<Country> response=countryController.getCountyById(countryId);
		
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertEquals(countryId,response.getBody().getId());
	}
	
	
	
	@Test
	@Order(3)
	public void test_getCountryByName() {
		
		country=new Country(2,"USA","Washington");
		String countryName="USA";
		
		
		when(countryService.getCountryByName(countryName)).thenReturn(country);
		ResponseEntity<Country> response=countryController.getCountryByName(countryName);
		
		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertEquals(countryName,response.getBody().getCountryName());
	}
	
	@Test
	@Order(4)
	public void test_addCountry() {
		
		country=new Country(3,"Germany","Berlin");
		
		when(countryService.addCountry(country)).thenReturn(country);
		ResponseEntity<Country> response=countryController.addCountry(country);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(country,response.getBody());
	}
	
	
	@Test
	@Order(5)
	public void test_updateCountry() {
		
		country=new Country(3,"Germany","Berlin");
		int countryId=3;
		
		when(countryService.getCountryById(countryId)).thenReturn(country);
		when(countryService.updateCountry(country)).thenReturn(country);
		
		ResponseEntity<Country> response=countryController.updateCountry(countryId,country);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(3,response.getBody().getId());
	}
	
	
	

}
