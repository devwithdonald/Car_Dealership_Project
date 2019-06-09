package com.donald.services;

import java.io.ObjectInputStream.GetField;
import java.util.Scanner;

import com.donald.dao.CustomerListSerializeDAO;
import com.donald.dao.OfferSerializeDAO;
import com.donald.users.Car;
import com.donald.users.CarLot;
import com.donald.users.Customer;
import com.donald.users.CustomerBase;
import com.donald.users.MasterOfferList;
import com.donald.users.Offer;
import com.donald.users.UniqueOrderID;
import com.donald.users.UniqueOrderIDCounter;
import com.donald.util.LoggingUtil;

public class CustomerServiceImpl implements CustomerServiceInt {

	CarLotServiceImpl cls = new CarLotServiceImpl();
	
	@Override
	public void makeOffer(Customer loggedInCustomer) {		
		LoggingUtil.trace("CustomerServiceImpl - makeOffer(); - start");
	
		String carIDInput = "";
		String offerPrice = "";
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please enter the car ID you would like to make an offer on -->");
		carIDInput = scanner.nextLine();
		
		System.out.println("Please enter your offer price -->");
		offerPrice = scanner.nextLine();
		
		Car offerCar = null;
		
		// TODO FIX
		Integer intCarIDInput = Integer.parseInt(carIDInput);
		Integer intOfferPrice = Integer.parseInt(offerPrice);
		
		//search through carlot list for car id
		offerCar = cls.matchCarId(intCarIDInput);

		//add offer car to the MASTER list
		MasterOfferList.getOfferlist().add(new Offer(offerCar, loggedInCustomer, intOfferPrice, getUniqueOfferId(1)));
		Offer newOffer = MasterOfferList.getOfferlist().get(MasterOfferList.getOfferlist().size() - 1);
		int newOfferID = newOffer.getOfferID();
		System.out.println("Offer ID ->" + newOfferID);
		offerCar.getCarOfferList().add(newOffer);
		
	}
	
	//TODO JUNIT TEST
	@Override
	public Integer getUniqueOfferId(Integer num) {
		LoggingUtil.trace("CustomerServiceImpl - getUniqueOfferId();");
		UniqueOrderID orderId = new UniqueOrderID(num);
		UniqueOrderIDCounter.getUniqeidcounter().add(orderId);
		Integer offerId = UniqueOrderIDCounter.getUniqeidcounter().size();
		
		return offerId;
		
	}

	@Override
	public void viewOwnedCars(Customer loggedInCustomer) {
		LoggingUtil.trace("CustomerServiceImpl - viewOwnedCars(); - start");

		
		if (loggedInCustomer.getCarsOwned().size() == 0) {
			LoggingUtil.warn("No cars in " + loggedInCustomer + " inventory");
			System.out.println("You Have No Cars In Your Inventory!");
		} else {
			for (int i = 0; i < loggedInCustomer.getCarsOwned().size(); i++) {
				System.out.println(loggedInCustomer.getCarsOwned().get(i));
			}
		}

	}

	@Override
	public void viewRemainingPayments(Customer loggedInCustomer) {
		LoggingUtil.trace("CustomerServiceImpl - viewRemainingPayments(); - start");
		System.out.println("-- Viewing Remaining Payments --");
		
		System.out.println(loggedInCustomer.getUsername() + " Has a Balance of " + loggedInCustomer.getBalance());

		System.out.println("And a Current Monthly Payment of " + Math.round(loggedInCustomer.getMonthlyPayment()));
		
		Double remainingPayments = loggedInCustomer.getBalance() / loggedInCustomer.getMonthlyPayment();
		
		System.out.println("Remaining Payments Left at The Current Monthly Payment ->" + Math.round(remainingPayments));
	}

	@Override
	public void viewLocalPaymentsMade(Customer loggedInCustomer) {
		// TODO Auto-generated method stub
		
	}

}
