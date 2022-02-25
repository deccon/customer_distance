package com.java.interview.integration;

import static org.junit.Assert.assertFalse;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.java.interview.DistanceCalculator;
import com.java.interview.entity.Customer;
import com.java.interview.entity.Location;
import com.java.interview.file.CustomerFileHelper;

/**
 * Integration test for creating customer invite list
 *
 */
public class CustomerInvitationTest {

	private DistanceCalculator distanceCalculator;
	private CustomerFileHelper customerFileHelper;
	private Location officeLocation;
	double maxInviteDistance = 100;

	@Before
	public void setUp() throws Exception {
		distanceCalculator = new DistanceCalculator();
		customerFileHelper = new CustomerFileHelper();
		officeLocation = new Location();
		
		officeLocation.setLatitude(53.3381985);
		officeLocation.setLongitude(-6.2592576);
	}

	@Test
	public void testCalculateDistanceForAllCustomers()
			throws FileNotFoundException {
		List<Customer> customers = customerFileHelper
				.readCustomerFile("customers.json");
		List<Customer> customerInviteList = new ArrayList<Customer>();

		for (Customer customer : customers) {
			double distanceToOffice = distanceCalculator.calculateDistance(
					officeLocation, customer);
			if (distanceToOffice <= maxInviteDistance) {
				customerInviteList.add(customer);
			}
		}

		customerFileHelper.writeCustomerFile(customerInviteList,
				"customer_invites.json");
		
		assertFalse("Customer list should not be empty", customers.isEmpty());
	}

}
