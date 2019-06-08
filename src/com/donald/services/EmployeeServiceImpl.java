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

public class EmployeeServiceImpl implements EmployeeServiceInt {

	CustomerListSerializeDAO customerListData = new CustomerListSerializeDAO();
	CarLotSerializeDAO carLotData = new CarLotSerializeDAO();
	OfferSerializeDAO offerListData = new OfferSerializeDAO();
	
	@Override
	public void acceptOffer() {
		System.out.println("-- Accept Offer Screen --");
		
		
		
		//load
		MasterOfferList.getOfferlist().clear();

		if (offerListData.loadOfferList() != null) {
			 MasterOfferList.getOfferlist().addAll(offerListData.loadOfferList());
		}
		
		//load
		CustomerBase.getCustomerlist().clear();

		if (customerListData.loadCustomerList() != null) {
			 CustomerBase.getCustomerlist().addAll(customerListData.loadCustomerList());
		}
		
		//load
		CarLot.getCarlot().clear();

		if (carLotData.loadCarLot() != null) {
			 CarLot.getCarlot().addAll(carLotData.loadCarLot());
		}

		Scanner scanner = new Scanner(System.in);

		String input = "";

		System.out.println("Enter Offer ID to accept-->");

		int index = 0;
		boolean removeIndex = false;

		input = scanner.nextLine();
		int intInput = Integer.parseInt(input);
		
		for (int i = 0; i < MasterOfferList.getOfferlist().size(); i++) {

			if (MasterOfferList.getOfferlist().get(i).getOfferID() == intInput) {
				index = i;
				removeIndex = true;
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
					if (buyer.getPendingOffers().get(j).getOfferID() == intInput) {
						buyer.getPendingOffers().remove(j);
					}
				}

				// set making payments to true if not already true
				if (!buyer.isMakingPayments()) {
					buyer.setMakingPayments(true);
				}



				// remove car with that unique offer id from the offer list - matching by carID
				// need to figure out remove every person that has had this car on their
				// offerList
				// TODO THIS NEEDS TO BE ITS OWN METHOD
				// TODO TO REMOVE THIS CAR FROM EVERYONE ELSE OFFER LIST
				for (int j = 0; j < MasterOfferList.getOfferlist().size(); j++) {
					
					System.out.println("carid msterlist>" + MasterOfferList.getOfferlist().get(j).getOfferCar().getCarID());
					System.out.println("carid offercar>" + offerCar.getCarID());
					
					if (MasterOfferList.getOfferlist().get(j).getOfferCar().getCarID() == offerCar.getCarID()) {
						// System.out.println("should remove->" +
						// MasterOfferList.getOfferlist().get(j));
						MasterOfferList.getOfferlist().remove(j);
					}
				}
				
				System.out.println("MASTEROFFERLISTSIZE ->" + MasterOfferList.getOfferlist().size());

				
				// set forSale false
				offerCar.setForSale(false);

				// setting car offer list to null
				offerCar.setCarOfferList(null);

				// setting username to care
				offerCar.setOwnerUsername(buyer.getUsername());
				
				// might have to set to null

				// remove car from lot
				for (int j = 0; j < CarLot.getCarlot().size(); j++) {
					if(CarLot.getCarlot().get(j).getCarID() == offerCar.getCarID()) {
						CarLot.getCarlot().remove(j);
					}
				}
				
				
				
				WebServiceImpl wsi = new WebServiceImpl();
				
				wsi.calculateMonthlyPayment(buyer);
				
				System.out.println("buyer->" + buyer.getUsername());
				System.out.println("buyers owned cars->" + buyer.getCarsOwned());
				
				
				//save
				customerListData.saveCustomerList(CustomerBase.getCustomerlist());
				carLotData.saveCarLot(CarLot.getCarlot());
				offerListData.saveOfferList(MasterOfferList.getOfferlist());
				
				System.out.println("buyer->" + buyer.getUsername());
				System.out.println("buyers owned cars->" + buyer.getCarsOwned());
				
				//CarLot.getCarlot().remove(offerCar.getCarID());

				// remove car from each customer list that his this car
				// go through customer list, check
//				for (int j = 0; j < CustomerBase.getCustomerlist().size(); j++) {
//					if(CustomerBase.getCustomerlist().get(j).getPendingOffers().get(j))
//				}
				
				//TODO MIGHT HAVE TO SAVE CUSTOMER LIST DATA AGAIN!!!
				
				//???????
				//CarLot.getCarlot().remove(offerCar);
				
				
			}

		}

		
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
					if (rejectCustomer.getPendingOffers().get(j).getOfferID() == intInput) {
						rejectCustomer.getPendingOffers().remove(j);
					}
				}

				// removing from the master list as well by ID
				// keep other offers

				MasterOfferList.getOfferlist().remove(i);

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
