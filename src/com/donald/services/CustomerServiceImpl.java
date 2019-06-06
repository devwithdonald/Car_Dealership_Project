package com.donald.services;

import java.util.Scanner;

import com.donald.users.Car;
import com.donald.users.CarLot;
import com.donald.users.Customer;
import com.donald.users.MasterOfferList;
import com.donald.users.Offer;

public class CustomerServiceImpl implements CustomerServiceInt {

	@Override
	public void makeOffer(Customer loggedInCustomer) {		
		//create new offer based on carID
		String carIDInput = "";
		String offerPrice = "";
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please enter the car ID you would like to make an offer on -->");
		carIDInput = scanner.nextLine();
		
		
		System.out.println("Please enter your offer price -->");
		offerPrice = scanner.nextLine();
		
		
		Car offerCar = null;
		
		//parse to int
		Integer intCarIDInput = Integer.parseInt(carIDInput);
		Integer intOfferPrice = Integer.parseInt(offerPrice);
		
		//search through carlot list for car id
		for (int i = 0; i < CarLot.getCarlot().size(); i++) {
			if (CarLot.getCarlot().get(i).getCarID() == intCarIDInput) {
				//return the car
				offerCar = CarLot.getCarlot().get(i);
			}
		}
		
		//make new offer object
		//add offer car to the MASTER list
		MasterOfferList.getOfferlist().add(new Offer(offerCar, loggedInCustomer, intOfferPrice));
		
		//get the offerID as confirmation!
		//offer just made
		Offer newOffer = MasterOfferList.getOfferlist().get(MasterOfferList.getOfferlist().size() - 1);
		
		int newOfferID = newOffer.getOfferID();
		System.out.println("Offer ID ->" + newOfferID);
		
		//get the offer just made and put it in the car list!
		offerCar.getCarOfferList().add(newOffer);
		
		//add an offer to that car lot offerList
		
		
		
		
		//add that car to the person offer list
		
	
		
		
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
	public void viewRemainingPayments(Customer loggedInCustomer) {
		// TODO Auto-generated method stub
		System.out.println(loggedInCustomer.getUsername() + " has a balance of " + loggedInCustomer.getBalance());

	
	}

	@Override
	public void viewLocalPaymentsMade(Customer loggedInCustomer) {
		// TODO Auto-generated method stub
		
	}

}
