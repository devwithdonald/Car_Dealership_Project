package com.donald.services;

import com.donald.users.Car;
import com.donald.users.Customer;

public interface CustomerServiceInt {

	public void makeOffer(Car car);
		
	public void viewOwnedCars(Customer loggedInCustomer);
	
	public void viewRemainingPayments();
	
	public void viewLocalPaymentsMade();
	
}
