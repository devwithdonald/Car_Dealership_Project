package com.donald.services;

import java.util.Scanner;

import com.donald.dao.OfferSerializeDAO;
import com.donald.users.Car;
import com.donald.users.CarLot;
import com.donald.users.Customer;
import com.donald.users.MasterOfferList;
import com.donald.users.MasterPaymentList;

public class WebServiceImpl implements WebServiceInt {

	OfferSerializeDAO offerListData = new OfferSerializeDAO();
	
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
	public void viewAllPayments() {
		System.out.println("-- View All Payments --");
		// TODO Auto-generated method stub
		// view every payment in the system
		
		
		
		if(MasterPaymentList.getMasterPaymentList().size() == 0) {
			System.out.println("Master payment list is empty!");
		} else {
			for (int i = 0; i < MasterPaymentList.getMasterPaymentList().size(); i++) {
				//print out ALL payments
				System.out.println(MasterPaymentList.getMasterPaymentList().get(i));
			}
		}
		
		
		
		
	
	}

	@Override
	public void calculateMonthlyPayment(Customer loggedInCustomer) {
		
		System.out.println("Current Customer Monthly Payment -->" + Math.round(loggedInCustomer.getMonthlyPayment()));
		
		loggedInCustomer.setMonthlyPayment(loggedInCustomer.getBalance() / 12.0);
		
		System.out.println("New Monthly Customer Monthly Payment -->" + Math.round(loggedInCustomer.getMonthlyPayment()));
	}
	
	
	
	
	
	@Override
	public void viewCarOfferList()	{
		System.out.println("-- View All Car Offers For Each Car --");
		
		
		//load
		//load
		MasterOfferList.getOfferlist().clear();

		if (offerListData.loadOfferList() != null) {
			 MasterOfferList.getOfferlist().addAll(offerListData.loadOfferList());
		}
		
		if(MasterOfferList.getOfferlist().size() == 0) {
			System.out.println("car offer list is empty");
		}else {
			for (int i = 0; i < MasterOfferList.getOfferlist().size(); i++) {
				System.out.println(MasterOfferList.getOfferlist().get(i));
			}
		}
		
		
		
	}

}
