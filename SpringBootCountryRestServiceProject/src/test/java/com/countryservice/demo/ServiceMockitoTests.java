package com.countryservice.demo;


import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

import com.countryservice.demo.repository.CountryRepository;
import com.countryservice.demo.services.CountryService;
import com.countryservice.demo.beans.Country;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {ServiceMockitoTests.class})
public class ServiceMockitoTests {
	
	@Mock
	CountryRepository countryRepository;
	
	@InjectMocks
	CountryService countryService;
	
	@Test
	@Order(1)
	public void test_getAllCountries() {
		
		List<Country> mockedCountries=new ArrayList<Country>();
		mockedCountries.add(new Country(1,"India","Delhi"));
		mockedCountries.add(new Country(2,"Japan","Tokyo"));
		
		
		when(countryRepository.findAll()).thenReturn(mockedCountries);
		assertEquals(2,countryService.getAllCountries().size());
	}
	
	@Test
	@Order(2)
	public void test_getCountryById() {
		
		List<Country> mockedCountries=new ArrayList<Country>();
		mockedCountries.add(new Country(1,"India","Delhi"));
		mockedCountries.add(new Country(2,"Japan","Tokyo"));
		int countryId=1;
		
		
		when(countryRepository.findAll()).thenReturn(mockedCountries);
		assertEquals(countryId,countryService.getCountryById(countryId).getId());
	}
	
	@Test
	@Order(3)
	public void test_addCountry() {
		
		Country country=new Country(3,"Germany","Berlin");
		when(countryRepository.save(country)).thenReturn(country);
		assertEquals(country,countryService.addCountry(country));
	}
	
	
	
	@Test
	@Order(4)
	public void test_deleteCountry() {
		
		Country country=new Country(3,"Germany","Berlin");
		countryService.deleteCountry(country.getId());
		verify(countryRepository, times(1)).delete(country);
		
	}
	
	

}
