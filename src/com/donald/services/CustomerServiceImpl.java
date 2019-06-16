package com.donald.services;

import java.util.Scanner;

import com.donald.sqldao.CarPostgresDAOImpl;
import com.donald.sqldao.OfferPostgresDAOImpl;
import com.donald.users.Car;
import com.donald.users.Customer;
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
		while (!scanner.hasNextInt()) {
			System.out.println("Please Enter a Valid Number.");
			scanner.next();
		}
		carId = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Please enter your offer price -->");
		while (!scanner.hasNextInt()) {
			System.out.println("Please Enter a Valid Number.");
			scanner.next();
		}
		offerPrice = scanner.nextInt();
		scanner.nextLine();

		if (carDAO.getCarById(carId) == null) {
			System.out.println("Invalid Choice. Boat is Not In The System.");
			return;
		}

		offerDAO.makeOffer(loggedInCustomer, carId, offerPrice);

		System.out.println("Offer succesfully input into the system. Thank you!");

	}

	@Override
	public void viewOwnedCars(Customer loggedInCustomer) {
		LoggingUtil.trace("CustomerServiceImpl - viewOwnedCars(); - start");

		if (carDAO.getCarsByCustomerId(loggedInCustomer.getCustomerID()).size() == 0) {
			LoggingUtil.warn("No cars in " + loggedInCustomer + " inventory");
			System.out.println("You Have No Boats In Your Inventory!");
		} else {
			for (Car car : carDAO.getCarsByCustomerId(loggedInCustomer.getCustomerID())) {
				System.out.println(car);
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
