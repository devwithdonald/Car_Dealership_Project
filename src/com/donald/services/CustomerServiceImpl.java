package com.donald.services;

import com.donald.users.Car;
import com.donald.users.Customer;

public class CustomerServiceImpl implements CustomerServiceInt {

	@Override
	public void makeOffer(Car car) {
		// TODO Auto-generated method stub

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
