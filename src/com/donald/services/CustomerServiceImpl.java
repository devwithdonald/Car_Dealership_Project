package com.donald.services;

import java.util.Scanner;

import com.donald.users.Customer;

public class CustomerServiceImpl implements CustomerServiceInt {

	@Override
	public void makeOffer(Customer loggedInCustomer) {
		// TODO Auto-generated method stub
		
		//create new offer based on carID
		String input = "";
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please enter the car ID you would like to make an offer on -->");
		
		input = scanner.nextLine();
		
		
		
		
		
		//view car list!
		

	}

	@Override
	public void viewOwnedCars(Customer loggedInCustomer) {
		// TODO Auto-generated method stub
		// have to their own car

		if (loggedInCustomer.getCarsOwned().size() == 0) {
			System.out.println("You have no cars in your lot!");
		} else {
			for (int i = 0; i < loggedInCustomer.getCarsOwned().size(); i++) {
				System.out.println(loggedInCustomer.getCarsOwned().get(i));
			}
		}

	}

	@Override
	public void viewRemainingPayments() {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewLocalPaymentsMade() {
		// TODO Auto-generated method stub

	}

}
