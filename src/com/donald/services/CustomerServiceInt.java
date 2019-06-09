package com.donald.services;

import com.donald.users.Car;
import com.donald.users.Customer;

public interface CustomerServiceInt {

	public void makeOffer(Customer loggedInCustomer);
		
	public void viewOwnedCars(Customer loggedInCustomer);
	
	public void viewRemainingPayments(Customer loggedInCustomer);
	
	public void viewLocalPaymentsMade(Customer loggedInCustomer);
	
	public Integer getUniqueOfferId(Integer num);
	
}
