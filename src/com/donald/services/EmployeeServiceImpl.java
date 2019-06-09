package com.donald.services;

import java.util.Scanner;

import com.donald.dao.CarLotSerializeDAO;
import com.donald.dao.CustomerListSerializeDAO;
import com.donald.dao.OfferSerializeDAO;
import com.donald.users.Car;
import com.donald.users.CarLot;
import com.donald.users.Customer;
import com.donald.users.CustomerBase;
import com.donald.users.MasterOfferList;
import com.donald.util.LoggingUtil;

public class EmployeeServiceImpl implements EmployeeServiceInt {

	@Override
	public void acceptOffer() {
		LoggingUtil.trace("EmployeeServiceImpl - acceptOffer() - start");
		System.out.println("-- Accept Offer Screen --");

		Scanner scanner = new Scanner(System.in);
		String input = "";

		System.out.println("Enter Offer ID to Accept --> ");
		input = scanner.nextLine();

		// TODO FIX
		int intInput = Integer.parseInt(input);

		for (int i = 0; i < MasterOfferList.getOfferlist().size(); i++) {

			if (MasterOfferList.getOfferlist().get(i).getOfferID() == intInput) {
				LoggingUtil.trace("acceptOffer(); - found matching ID");

				Car offerCar = MasterOfferList.getOfferlist().get(i).getOfferCar();

				Customer buyer = null;

				for (Customer c : CustomerBase.getCustomerlist()) {
					if (c.equals(MasterOfferList.getOfferlist().get(i).getOfferer())) {
						buyer = c;
					}
				}

				// adding car to customer car list
				buyer.getCarsOwned().add(offerCar);

				// setting buyer new overall balance to past balance + offer balance
				buyer.setBalance(buyer.getBalance() + MasterOfferList.getOfferlist().get(i).getOfferPrice());

				// remove pending offer from buyer where the unique id match
				for (int j = 0; j < buyer.getPendingOffers().size(); j++) {
					if (buyer.getPendingOffers().get(j).getOfferID() == intInput) {
						buyer.getPendingOffers().remove(j);
					}
				}

				// set making payments to true if not already true
				if (!buyer.isMakingPayments()) {
					buyer.setMakingPayments(true);
				}

				// TODO MAKE OWN METHOD
				// remove car with that unique offer id from the offer list - matching by carID
				for (int j = 0; j < MasterOfferList.getOfferlist().size(); j++) {
					if (MasterOfferList.getOfferlist().get(j).getOfferCar().getCarID() == offerCar.getCarID()) {
						MasterOfferList.getOfferlist().remove(j);
					}
				}

				// TODO MAKE OWN METHOD
				// set forSale false
				offerCar.setForSale(false);

				// setting car offer list to null
				offerCar.setCarOfferList(null);

				// setting username to care
				offerCar.setOwnerUsername(buyer.getUsername());

				// TODO MAKE OWN METHOD
				// remove car from lot
				for (int j = 0; j < CarLot.getCarlot().size(); j++) {
					if (CarLot.getCarlot().get(j).getCarID() == offerCar.getCarID()) {
						CarLot.getCarlot().remove(j);
					}
				}

				WebServiceImpl wsi = new WebServiceImpl();

				wsi.calculateMonthlyPayment(buyer);

			}

		}

	}

	@Override
	public void rejectOffer() {
		LoggingUtil.trace("EmployeeServiceImpl - rejectOffer() - start");
		System.out.println("-- Reject Offer Screen --");

		Scanner scanner = new Scanner(System.in);
		String input = "";

		System.out.println("Enter Offer ID to reject-->");
		input = scanner.nextLine();
		// TODO FIX
		int intInput = Integer.parseInt(input);

		for (int i = 0; i < MasterOfferList.getOfferlist().size(); i++) {
			if (MasterOfferList.getOfferlist().get(i).getOfferID() == intInput) {

				// getting the customer (reject)
				Customer rejectCustomer = MasterOfferList.getOfferlist().get(i).getOfferer();

				// remove pending offer from buyer where the unique id match
				for (int j = 0; j < rejectCustomer.getPendingOffers().size(); j++) {
					if (rejectCustomer.getPendingOffers().get(j).getOfferID() == intInput) {
						rejectCustomer.getPendingOffers().remove(j);
					}
				}

				// removing from the master list as well by ID, keep other offers
				MasterOfferList.getOfferlist().remove(i);

			}
		}

	}

	@Override
	public void offerDecisionMenu() {
		LoggingUtil.trace("CarLotServiceImpl - addCar(); - start");

		System.out.println("-- Offer Decision Screen --");

		WebServiceImpl wsi = new WebServiceImpl();

		boolean exitInput = false;
		String input = "";

		do {
			LoggingUtil.trace("CarLotServiceImpl - do loop - start");

			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter '1': To view the entire car offer list");
			System.out.println("Enter '2': To ACCEPT a car offer");
			System.out.println("Enter '3': To REJECT a car offer");
			System.out.println("Enter '0': Exit!");

			input = scanner.nextLine();

			if (input.equals("1")) {
				LoggingUtil.trace("do loop - accept/reject decision menu - calling viewCarOfferList();");
				wsi.viewCarOfferList();
				exitInput = false;
			} else if (input.equals("2")) {
				LoggingUtil.trace("do loop - accept/reject decision menu - calling acceptOffer();");
				acceptOffer();
				exitInput = false;
			} else if (input.equals("3")) {
				LoggingUtil.trace("do loop - accept/reject decision menu - calling rejectOffer();");
				rejectOffer();
				exitInput = false;
			} else if (input.equals("0")) {
				LoggingUtil.trace("do loop - accept/reject decision menu - exiting");
				exitInput = true;
			}

		} while (!exitInput);

	}

}
