package com.donald.services;

import java.util.Scanner;

import com.donald.dao.CustomerListSerializeDAO;
import com.donald.dao.OfferSerializeDAO;
import com.donald.users.Car;
import com.donald.users.CarLot;
import com.donald.users.Customer;
import com.donald.users.CustomerBase;
import com.donald.users.MasterOfferList;
import com.donald.users.Offer;

public class CustomerServiceImpl implements CustomerServiceInt {

	/*
	 * CustomerListSerializeDAO customerListData = new CustomerListSerializeDAO();
	 * OfferSerializeDAO offerListData = new OfferSerializeDAO();
	 */
	
	@Override
	public void makeOffer(Customer loggedInCustomer) {		
		
		//TODO load offer list - make own method
		
		
		/*
		 * MasterOfferList.getOfferlist().clear();
		 * 
		 * if (offerListData.loadOfferList() != null) {
		 * MasterOfferList.getOfferlist().addAll(offerListData.loadOfferList()); }
		 */
		
		
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
		
		/*
		 * //save offer list
		 * offerListData.saveOfferList(MasterOfferList.getOfferlist()); //save customer
		 * list customerListData.saveCustomerList(CustomerBase.getCustomerlist());
		 */

		

	}

	@Override
	public void viewOwnedCars(Customer loggedInCustomer) {
		// TODO Auto-generated method stub
		// have to their own car
		
		
		//load
//		CustomerBase.getCustomerlist().clear();
//		
//		if(customerListData.loadCustomerList() != null) {
//			CustomerBase.getCustomerlist().addAll(customerListData.loadCustomerList());
//		}
		System.out.println("customerusername=" + loggedInCustomer.getUsername());
		System.out.println("customercarowned=" + loggedInCustomer.getCarsOwned());
		
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
		System.out.println("-- Viewing Remaining Payments --");
		
		System.out.println(loggedInCustomer.getUsername() + " has a balance of " + loggedInCustomer.getBalance());

		System.out.println("and a current monthly payment of " + Math.round(loggedInCustomer.getMonthlyPayment()));
		
		Double remainingPayments = loggedInCustomer.getBalance() / loggedInCustomer.getMonthlyPayment();
		
		System.out.println("Remaining payments left at the current monthly payment ->" + Math.round(remainingPayments));
	}

	@Override
	public void viewLocalPaymentsMade(Customer loggedInCustomer) {
		// TODO Auto-generated method stub
		
	}

}
