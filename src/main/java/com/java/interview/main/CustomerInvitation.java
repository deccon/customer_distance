package com.java.interview.main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.java.interview.DistanceCalculator;
import com.java.interview.entity.Customer;
import com.java.interview.entity.Location;
import com.java.interview.file.CustomerFileHelper;

/**
 * CustomerInvitation class to read all customers and produce a list of customers to invite 
 *
 */
public class CustomerInvitation {

	private DistanceCalculator distanceCalculator;
	private CustomerFileHelper customerFileHelper;
	private Location officeLocation;
	
	public static double MAX_INVITE_DISTANCE = 100;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		CustomerInvitation customerInvite = new CustomerInvitation();
		customerInvite.compileInvites();
	}

	/*
	 * Produces a file of eligible customers for invitation
	 */
	private void compileInvites() throws FileNotFoundException {
		distanceCalculator = new DistanceCalculator();
		customerFileHelper = new CustomerFileHelper();
		officeLocation = new Location(53.3381985, -6.2592576);
		
		String customerFileName = "customers.json";
		String inviteFileName = "customer_invites.json";

		List<Customer> customers = customerFileHelper
				.readCustomerFile(customerFileName);
		
		List<Customer> customerInviteList = getCustomerInviteList(customers);
		
		customerFileHelper.writeCustomerFile(customerInviteList,inviteFileName);
		
		System.out.println("Customer invite list created!");
	}

	/*
	 * Return a list of customers that live within the required distance
	 */
	private List<Customer> getCustomerInviteList(List<Customer> customers) {
		List<Customer> customerInviteList = new ArrayList<Customer>();

		for (Customer customer : customers) {
			double distanceToOffice = distanceCalculator.calculateDistance(
					officeLocation, customer);
			
			if (distanceToOffice <= MAX_INVITE_DISTANCE) {
				customerInviteList.add(customer);
			}
		}
		return customerInviteList;
	}
}
