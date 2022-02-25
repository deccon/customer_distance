package com.java.interview;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.java.interview.entity.Customer;
import com.java.interview.entity.Location;

public class DistanceCalculatorTest {
	
	private DistanceCalculator distanceCalculator;
	private Location officeLocation;
	private Customer customer;
	
	@Before
	public void setUp() throws Exception {
		distanceCalculator = new DistanceCalculator();
		double latitude = 53.3381985;
		double longitude = -6.2592576;
		
		officeLocation = new Location(); 
		
		officeLocation.setLatitude(latitude);
		officeLocation.setLongitude(longitude);
		
		customer = new Customer("John Smith", 123, 52.986375, -6.043701);
	}

	@Test
	public void testDistanceToCustomer() {
		
		double result = distanceCalculator.calculateDistance(officeLocation, customer);
		assertTrue("Distance returned should be greater than zero", result > 0);
	}

}
