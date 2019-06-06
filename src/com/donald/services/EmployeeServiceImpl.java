package com.donald.services;

import java.util.Scanner;

import com.donald.users.Car;
import com.donald.users.CarLot;
import com.donald.users.Customer;
import com.donald.users.MasterOfferList;

public class EmployeeServiceImpl implements EmployeeServiceInt {

	@Override
	public void acceptOffer() {
		System.out.println("-- Accept Offer Screen --");

		Scanner scanner = new Scanner(System.in);

		String input = "";

		System.out.println("Enter Offer ID to accept-->");

		input = scanner.nextLine();
		int intInput = Integer.parseInt(input);

		for (int i = 0; i < MasterOfferList.getOfferlist().size(); i++) {
			if (MasterOfferList.getOfferlist().get(i).getOfferID() == intInput) {

				// getting the offer car
				Car offerCar = MasterOfferList.getOfferlist().get(i).getOfferCar();

				// getting the customer (buyer)
				Customer buyer = MasterOfferList.getOfferlist().get(i).getOfferer();

				// adding car to customer car list
				buyer.getCarsOwned().add(offerCar);

				// setting buyer new overall balance to past balance + offer balance
				buyer.setBalance(buyer.getBalance() + MasterOfferList.getOfferlist().get(i).getOfferPrice());

				// remove pending offer from buyer where the unique id match
				for (int j = 0; j < buyer.getPendingOffers().size(); j++) {
					if (buyer.getPendingOffers().get(i).getOfferID() == intInput) {
						buyer.getPendingOffers().remove(i);
					}
				}

				// set making payments to true if not already true
				if (!buyer.isMakingPayments()) {
					buyer.setMakingPayments(true);
				}

				// set forSale false
				offerCar.setForSale(false);

				// setting car offer list to null
				offerCar.setCarOfferList(null);

				// setting username to care
				offerCar.setOwnerUsername(buyer.getUsername());

				// remove car with that unique offer id from the offer list - matching by carID
				// need to figure out remove every person that has had this car on their
				// offerList
				// TODO THIS NEEDS TO BE ITS OWN METHOD
				// TODO TO REMOVE THIS CAR FROM EVERYONE ELSE OFFER LIST
				for (int j = 0; j < MasterOfferList.getOfferlist().size(); j++) {
					if (MasterOfferList.getOfferlist().get(i).getOfferCar().getCarID() == offerCar.getCarID()) {
						MasterOfferList.getOfferlist().remove(i);
					}
				}

				// remove car from lot
				CarLot.getCarlot().remove(offerCar);

			}
		}

		// go through the car offer list and pick the car id that matches

	}

	@Override
	public void rejectOffer() {
		System.out.println("-- Reject Offer Screen --");
		Scanner scanner = new Scanner(System.in);

		String input = "";

		System.out.println("Enter Offer ID to reject-->");

		input = scanner.nextLine();
		int intInput = Integer.parseInt(input);

		for (int i = 0; i < MasterOfferList.getOfferlist().size(); i++) {
			if (MasterOfferList.getOfferlist().get(i).getOfferID() == intInput) {
				// reject logic

				// getting the customer (reject)
				Customer rejectCustomer = MasterOfferList.getOfferlist().get(i).getOfferer();

				// take offer out
				// remove pending offer from buyer where the unique id match
				for (int j = 0; j < rejectCustomer.getPendingOffers().size(); j++) {
					if (rejectCustomer.getPendingOffers().get(i).getOfferID() == intInput) {
						rejectCustomer.getPendingOffers().remove(i);
					}
				}
				
				// removing from the master list as well by ID
				// keep other offers 
				for (int j = 0; j < MasterOfferList.getOfferlist().size(); j++) {
					if (MasterOfferList.getOfferlist().get(i).getOfferID() == intInput) {
						MasterOfferList.getOfferlist().remove(i);
					}
				}
				
				
				
			}
		}

	}

	@Override
	public void offerDecision() {
		System.out.println("-- Offer Decision Screen --");

		WebServiceImpl wsi = new WebServiceImpl();

		boolean exitInput = false;
		String input = "";

		do {
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter '1': To view the entire car offer list");
			System.out.println("Enter '2': To ACCEPT a car offer");
			System.out.println("Enter '3': To REJECT a car offer");
			System.out.println("Enter '0': Exit!");

			input = scanner.nextLine();

			if (input.equals("1")) {
				wsi.viewCarOfferList();
				exitInput = false;
			} else if (input.equals("2")) {
				acceptOffer();
				exitInput = false;
			} else if (input.equals("3")) {
				rejectOffer();
				exitInput = false;
			} else if (input.equals("0")) {
				exitInput = true;
			}

		} while (!exitInput);

	}

}
