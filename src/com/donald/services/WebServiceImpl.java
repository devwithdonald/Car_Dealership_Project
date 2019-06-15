package com.donald.services;

import java.util.Scanner;

import com.donald.dao.OfferSerializeDAO;
import com.donald.users.Car;
import com.donald.users.CarLot;
import com.donald.users.Customer;
import com.donald.users.MasterOfferList;
import com.donald.users.MasterPaymentList;
import com.donald.util.LoggingUtil;

public class WebServiceImpl implements WebServiceInt {



	@Override
	public String initialScreen() {
		LoggingUtil.trace("WebServiceImpl - initialScreen(); - start");
		
		System.out.println("Hello & welcome to Boat Buddies! Boat Buddies is here to give you \nthe best boat buying experience a person can dream of.");

		boolean validInput = false;
		String input = "";

		// checking for correct input
		do {
			Scanner scanner = new Scanner(System.in);

			System.out.println("Please enter the correct identifier. Employee (e) or Customer (c)");
			System.out.println("--> or enter '0' to exit.");

			input = scanner.nextLine();

			if (input.equals("E") || input.equals("e")) {
				LoggingUtil.trace("WebServiceImpl - input = 'employee'");
				input = "employee";
				validInput = true;
			} else if (input.equals("C") || input.equals("c")) {
				LoggingUtil.trace("WebServiceImpl - input = 'customer'");
				input = "customer";
				validInput = true;
			} else if (input.equals("0")) {
				LoggingUtil.trace("WebServiceImpl - input = '0'");
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
	public void viewAllPayments() {
		LoggingUtil.trace("WebServiceImpl - viewAllPayments(); - start");
		System.out.println("-- View All Payments --");

		if (MasterPaymentList.getMasterPaymentList().size() == 0) {
			System.out.println("Master Payment List is Empty!");
		} else {
			for (int i = 0; i < MasterPaymentList.getMasterPaymentList().size(); i++) {
				System.out.println(MasterPaymentList.getMasterPaymentList().get(i));
			}
		}

	}

	@Override
	public void calculateMonthlyPayment(Customer loggedInCustomer) {

		System.out.println("Current Customer Monthly Payment -->" + Math.round(loggedInCustomer.getMonthlyPayment()));

		loggedInCustomer.setMonthlyPayment(loggedInCustomer.getBalance() / 12.0);

		System.out
				.println("New Monthly Customer Monthly Payment -->" + Math.round(loggedInCustomer.getMonthlyPayment()));
	}

	@Override
	public void viewCarOfferList() {
		LoggingUtil.trace("WebServiceImpl - viewCarOfferList(); - start");
		
		System.out.println("-- View All Boat Offers For Each Boat --");

		if (MasterOfferList.getOfferlist().size() == 0) {
			LoggingUtil.debug("viewCarOfferList -> Boat Offer List is Empty");
			System.out.println("Boat Offer List is Empty");
		} else {
			LoggingUtil.debug("viewCarOfferList -> Boat Offer List is NOT Empty");
			for (int i = 0; i < MasterOfferList.getOfferlist().size(); i++) {
				System.out.println(MasterOfferList.getOfferlist().get(i));
			}
		}

	}

}
