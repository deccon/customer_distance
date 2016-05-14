package com.java.interview.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.interview.entity.Customer;

/**
 * CustomerFileHelper will enable read/write operations on customer files
 *
 */
public class CustomerFileHelper {
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Read in JSON file with customer information
	 * 
	 * @param fileName
	 * @return list of customer objects
	 * 
	 * @throws FileNotFoundException
	 */
	public List<Customer> readCustomerFile(String fileName)
			throws FileNotFoundException {
		List<Customer> customerList = null;
		File file = getCustomerFile(fileName);

		try {
			JsonParser parser = new JsonFactory().createParser(file);
			customerList = mapper.readValues(parser, Customer.class).readAll();
		} catch (IOException e) {
			throw new RuntimeException("Could not read from file", e);
		}

		return customerList;
	}

	/**
	 * Write out JSON file with customer information
	 * 
	 * @param customerList
	 * @param fileName
	 * @return 
	 */
	public File writeCustomerFile(List<Customer> customerList, String fileName) {
		File outputFile;
		
		try {
			outputFile = new File(fileName);
			final OutputStream out = new FileOutputStream(outputFile);
			mapper.writeValue(out, customerList);
		} catch (Exception e) {
			throw new RuntimeException("Could not write to file", e);
		}
		
		return outputFile;
	}

	/*
	 * Get customer file given fileName
	 */
	private File getCustomerFile(String fileName) throws FileNotFoundException {
		File customerFile;

		try {
			ClassLoader classLoader = getClass().getClassLoader();
			customerFile = new File(classLoader.getResource(fileName).getFile());
		} catch (NullPointerException e) {
			throw new FileNotFoundException("Filename provided was not found");
		}
		return customerFile;
	}
}
