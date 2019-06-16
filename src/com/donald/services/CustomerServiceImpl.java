package com.donald.services;

import java.util.Scanner;

import com.donald.sqldao.CarPostgresDAOImpl;
import com.donald.sqldao.OfferPostgresDAOImpl;
import com.donald.users.Car;
import com.donald.users.Customer;
import com.donald.users.MasterOfferList;
import com.donald.users.Offer;
import com.donald.users.UniqueOrderID;
import com.donald.users.UniqueOrderIDCounter;
import com.donald.util.LoggingUtil;

public class CustomerServiceImpl implements CustomerServiceInt {

	OfferPostgresDAOImpl offerDAO = new OfferPostgresDAOImpl();
	CarPostgresDAOImpl carDAO = new CarPostgresDAOImpl();
	
	CarLotServiceImpl cls = new CarLotServiceImpl();
	
	@Override
	public void makeOffer(Customer loggedInCustomer) {		
		LoggingUtil.trace("CustomerServiceImpl - makeOffer(); - start");
	
		Integer carId;
		Integer offerPrice;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please enter the boat ID you would like to make an offer on -->");
		while(!scanner.hasNextInt()) {
			System.out.println("Please Enter a Valid Number.");
		    scanner.next();
		}
		carId = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Please enter your offer price -->");
		while(!scanner.hasNextInt()) {
			System.out.println("Please Enter a Valid Number.");
		    scanner.next();
		}
		offerPrice = scanner.nextInt();
		scanner.nextLine();

		
		
		
		if(carDAO.getCarById(carId) == null) {
			System.out.println("Invalid Choice. Boat is Not In The System.");
			return;
		}
		
		//Car offerCar = null;
		//offerCar = cls.matchCarId(carIDInput);
			
		//if(offerCar == null) {
		//	System.out.println("Invalid Choice. Boat is Not In The System.");
		//	return;
		//}
		
		
		//boat id and price
		offerDAO.makeOffer(loggedInCustomer, carId, offerPrice);
		//TODO GIVE ID BACK
		
		//addOfferToMasterOfferList(offerCar, loggedInCustomer, offerPrice);
				
	}
	
	@Override
	public void addOfferToMasterOfferList(Car offerCar, Customer loggedInCustomer, Integer offerPrice) {
		LoggingUtil.trace("CustomerServiceImpl - addOfferToMasterOfferList(); - start");
		
		
		//add offer car to the MASTER list
		MasterOfferList.getOfferlist().add(new Offer(offerCar, loggedInCustomer, offerPrice, getUniqueOfferId(1)));
		Offer newOffer = MasterOfferList.getOfferlist().get(MasterOfferList.getOfferlist().size() - 1);
		int newOfferID = newOffer.getOfferID();
		System.out.println("Offer ID ->" + newOfferID);
		offerCar.getCarOfferList().add(newOffer);
	}
	

	
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

		if(carDAO.getCarsByCustomerId(loggedInCustomer.getCustomerID()).size() == 0) {
			LoggingUtil.warn("No cars in " + loggedInCustomer + " inventory");
			System.out.println("You Have No Boats In Your Inventory!");
		} else {
			for (Car car : carDAO.getCarsByCustomerId(loggedInCustomer.getCustomerID())) {
				System.out.println(car);
			}
		}
		
//		if (loggedInCustomer.getCarsOwned().size() == 0) {
//			LoggingUtil.warn("No cars in " + loggedInCustomer + " inventory");
//			System.out.println("You Have No Boats In Your Inventory!");
//		} else {
//			for (int i = 0; i < loggedInCustomer.getCarsOwned().size(); i++) {
//				System.out.println(loggedInCustomer.getCarsOwned().get(i));
//			}
//		}

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
