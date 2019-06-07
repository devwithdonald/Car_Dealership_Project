package com.donald.services;

import com.donald.users.Car;
import com.donald.users.Customer;

public interface WebServiceInt {
	
	// return the screen
	public String initialScreen();
	
	public void viewAllPayments();
	
	public void calculateMonthlyPayment(Customer loggedInCustomer);
	
	public void viewCarOfferList();
	
	
	
	
	
}
