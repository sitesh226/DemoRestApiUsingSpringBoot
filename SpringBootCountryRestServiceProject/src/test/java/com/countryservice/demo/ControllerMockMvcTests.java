package com.countryservice.demo;

import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.controllers.CountryController;
import com.countryservice.demo.services.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan("com.countryservice.demo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes= {ControllerMockMvcTests.class})
public class ControllerMockMvcTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	CountryService countryService;
	
	@InjectMocks
	CountryController countryController;
	
	List<Country> mockedCountries;
	Country country;
	
	@BeforeEach
	public void setup() {
		mockMvc=MockMvcBuilders.standaloneSetup(countryController).build();
	}
	
	

	
	@Test
	@Order(1)
	public void test_getAllCountries() throws Exception {
		
		mockedCountries=new ArrayList<Country>();
		mockedCountries.add(new Country(1,"India","Delhi"));
		mockedCountries.add(new Country(2,"Japan","Tokyo"));
		
		
		when(countryService.getAllCountries()).thenReturn(mockedCountries);
		
		this.mockMvc.perform(get("/getcountries"))
					.andExpect(status().isFound())
					.andDo(print());
		
	}
	

	@Test
	@Order(2)
	public void test_getCountryById() throws Exception {
		
		country=new Country(1,"India","New Delhi");
		int countryId=1;
		
		when(countryService.getCountryById(countryId)).thenReturn(country);

		this.mockMvc.perform(get("/getcountries/{id}",countryId))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("India"))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("New Delhi"))
		.andDo(print());
	}
	
	
	@Test
	@Order(3)
	public void test_getCountryByName() throws Exception {
		
		country=new Country(2,"USA","Washinton DC");
		String countryName="USA";
		
		when(countryService.getCountryByName(countryName)).thenReturn(country);

		this.mockMvc.perform(get("/getcountries/countryname").param("name", "USA"))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".id").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("USA"))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Washinton DC"))
		.andDo(print());
	}
	
	
	
	@Test
	@Order(4)
	public void test_addCountry() throws Exception {
		
		country=new Country(3,"Germany","Berlin");
		
		when(countryService.addCountry(country)).thenReturn(country);
		
		ObjectMapper mapper=new ObjectMapper();
		String jsonBody=mapper.writeValueAsString(country);
		
		this.mockMvc.perform(post("/addcountry")
				             .content(jsonBody)
				             .contentType(MediaType.APPLICATION_JSON))
							 .andExpect(status().isCreated())
							 .andDo(print());
				     
		
	}
	
	
	@Test
	@Order(5)
	public void test_deleteCountry() throws Exception {
		
		country=new Country(3,"Japan","Tokyo");
		int countryId=3;
		
		when(countryService.getCountryById(countryId)).thenReturn(country);

		this.mockMvc.perform(get("/deletecountry/{id}",countryId))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	

}
