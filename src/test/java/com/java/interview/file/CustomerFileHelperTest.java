package com.java.interview.file;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.java.interview.entity.Customer;

public class CustomerFileHelperTest {
	
	private CustomerFileHelper customerFileHelper;
	private String customerFileName = "customers.json";
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() {
		customerFileHelper = new CustomerFileHelper();
	}

	@Test
	public void testReadCustomerFile() throws FileNotFoundException {
		List<Customer> customers = customerFileHelper.readCustomerFile(customerFileName);
		
		assertFalse("Customer list should not be empty", customers.isEmpty());
	}
	
	@Test
	public void testReadWrongFile() throws FileNotFoundException {
		
		thrown.expect(FileNotFoundException.class);
	    thrown.expectMessage("Filename provided was not found");
		
	    List<Customer> customers = customerFileHelper.readCustomerFile("no_file.json");
		assertTrue("Customer list should be empty", customers.isEmpty());
	}
	
	@Test
	public void testWriteCustomerFile() throws FileNotFoundException {
		
	    List<Customer> customers = customerFileHelper.readCustomerFile(customerFileName);
	    assertFalse("Customer list should not be empty", customers.isEmpty());

	    File outputFile = customerFileHelper.writeCustomerFile(customers, "customer_invites_test.json");
	    assertTrue("Test file does not exist", outputFile.exists());
	}
	
	@Test
	public void testWriteEmptyCustomerFile() throws FileNotFoundException {
		
	    List<Customer> customers = new ArrayList<Customer>();
	    assertTrue("Customer list should be empty", customers.isEmpty());

	    File outputFile = customerFileHelper.writeCustomerFile(customers, "customer_invites_test.json");
	    assertTrue("Test file does not exist", outputFile.exists());
	}
}
