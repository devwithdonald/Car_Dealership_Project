package com.donald.services;

import java.util.Scanner;

import com.donald.users.MasterPaymentList;

public class WebServiceImpl implements WebServiceInt {

	@Override
	public String initialScreen() {
		/*
		 * TODO This should show the initial screen & send the User to either the login
		 * or register screen return the String they need to be at (main should handle
		 * this)
		 */

		boolean validInput = false;
		String input = "";

		// greeting message
		System.out.println("Hello and welcome.");

		// checking for correct input
		do {
			Scanner scanner = new Scanner(System.in);

			System.out.println("Please enter the correct identifier. Employee (e) or Customer (c)");
			System.out.println("--> or enter '0' to exit.");

			input = scanner.nextLine();

			if (input.equals("E") || input.equals("e")) {
				input = "employee";
				validInput = true;
			} else if (input.equals("C") || input.equals("c")) {
				input = "customer";
				validInput = true;
			} else if (input.equals("0")){
				input = "0";
				validInput = true;
			}

		} while (!validInput);

		if (input.equals("0")) {
			System.exit(0);
		}
		
		return input;
	}

	@Override
	public boolean loginVerification() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void viewAllPayments() {
		System.out.println("-- View all payments --");
		// TODO Auto-generated method stub
		// view every payment in the system
		MasterPaymentList mpl = new MasterPaymentList();
		
		for (int i = 0; i < mpl.getMasterPaymentList().size(); i++) {
			//print out ALL payments
			System.out.println(mpl.getMasterPaymentList().get(i));
		}
		
	
	}

	@Override
	public int calculateMonthlyPayment() {
		// TODO Auto-generated method stub
		return 0;
	}

}
