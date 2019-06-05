package com.donald.services;

import com.donald.screens.Screen;

public interface WebServiceInt {
	
	// return the string screen 
	public String initialScreen();
	
	public boolean loginVerification();

	public void viewAllPayments();
	
	public int calculateMonthlyPayment();
	
	
}
