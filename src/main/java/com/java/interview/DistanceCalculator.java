package com.java.interview;

import com.java.interview.entity.Customer;
import com.java.interview.entity.Location;

/**
 * DistanceCalculator uses longitude and latitude to calculate distance
 *
 */
public class DistanceCalculator {

	/**
	 * Take a customer object and calculate the distance from a given location
	 * 
	 * @param startLocation
	 * @param customer
	 * @return distance in miles
	 */
	public double calculateDistance(Location startLocation, Customer customer) {
		double latitude1 = startLocation.getLatitude();
		double longtitude1 = startLocation.getLongitude();
		double latitude2 = customer.getLatitude();
		double longtitude2 = customer.getLongitude();
		
		float distance = distanceFormula(latitude1, longtitude1, latitude2, longtitude2);
		float distanceInMiles = convertToMiles(distance);
		
		return distanceInMiles;
	}
	
	/*
	 * Use a formula to calculate distance given two points
	 */
	private float distanceFormula(double latitude1, double longtitude1, double latitude2, double longtitude2) {
		double radiusOfEarth = 6371;
		double differenceLat = Math.toRadians(latitude2 - latitude1);
		double differenceLng = Math.toRadians(longtitude2 - longtitude1);
		
		double a = Math.sin(differenceLat / 2) * Math.sin(differenceLat / 2)
				+ Math.cos(Math.toRadians(latitude1))
				* Math.cos(Math.toRadians(latitude2)) * Math.sin(differenceLng / 2)
				* Math.sin(differenceLng / 2);
		
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		float dist = (float) (radiusOfEarth * c) * 1000;

		return dist;
	}

	/*
	 * Convert meters to miles
	 */
	private float convertToMiles(float distance) {
		return distance * 0.000621371f;
	}
}
