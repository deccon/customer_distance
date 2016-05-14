package com.java.interview.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {
	
	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "user_id")
	private int userId;
	
	@JsonProperty(value = "latitude")
	private double latitude;
	
	@JsonProperty(value = "longitude")
	private double longitude;
	
	public Customer() {
		
	}

	public Customer(String name, int userId, double latitude, double longitude) {
		this.name = name;
		this.userId = userId;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public int getUserId() {
		return userId;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
}
