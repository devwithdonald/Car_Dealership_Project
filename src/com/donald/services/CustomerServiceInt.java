package com.donald.services;

import com.donald.users.Car;

public interface CustomerServiceInt {

	public void makeOffer(Car car);
		
	public void viewOwnedCars();
	
	public void viewRemainingPayments();
	
	public void viewLocalPaymentsMade();
	
}
