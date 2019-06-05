package com.donald.services;

import com.donald.screens.UserScreen;

public interface WebServiceInt {
	
	// return the screen
	public String initialScreen();
	
	public boolean loginVerification();

	public void viewAllPayments();
	
	public int calculateMonthlyPayment();
	
	
	
	
}
