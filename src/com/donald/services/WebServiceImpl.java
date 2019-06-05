package com.donald.services;

import java.util.Scanner;


public class WebServiceImpl implements WebServiceInt{

	@Override
	public String initialScreen() {
		/*
		 *  TODO This should show the initial screen 
		 *   & send the User to either the login or register screen
		 *   return the String they need to be at (main should handle this)
		 */
		
		boolean validInput = false;
		String input = "";
		

		// greeting message
		System.out.println("Hello and welcome.");

		// checking for correct input
		do {
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Please enter the correct identifier. Employee (e) or Customer (c)");

			input = scanner.nextLine();

			if (input.equals("E") || input.equals("e")) {
				input = "employee";
				validInput = true;
			} else if (input.equals("C") || input.equals("c")) {
				input = "customer";
				validInput = true;
			}

		} while (!validInput);

		
		return input;
	}
	
	

	@Override
	public boolean loginVerification() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void viewAllPayments() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int calculateMonthlyPayment() {
		// TODO Auto-generated method stub
		return 0;
	}

}
