package com.donald.services;

import java.util.Scanner;

import com.donald.sqldao.CarPostgresDAOImpl;
import com.donald.sqldao.CustomerPostGresDAOImpl;
import com.donald.sqldao.OfferPostgresDAOImpl;
import com.donald.users.Car;
import com.donald.users.Customer;
import com.donald.users.CustomerBase;
import com.donald.users.MasterOfferList;
import com.donald.util.LoggingUtil;

public class EmployeeServiceImpl implements EmployeeServiceInt {

	private static CarPostgresDAOImpl carDAO;
	private static OfferPostgresDAOImpl offerDAO;
	private static CustomerPostGresDAOImpl customerDAO;
	
	@Override
	public void acceptOffer() {
		LoggingUtil.trace("EmployeeServiceImpl - acceptOffer() - start");
		System.out.println("-- Accept Offer Screen --");

		Scanner scanner = new Scanner(System.in);
		Integer acceptId;
		boolean accepted = false;

		System.out.println("Enter Offer ID to Accept --> ");
		while (!scanner.hasNextInt()) {
			System.out.println("Please Enter a Valid Number.");
			scanner.next();
		}
		acceptId = scanner.nextInt();
		scanner.nextLine();

		for (int i = 0; i < MasterOfferList.getOfferlist().size(); i++) {

			if (MasterOfferList.getOfferlist().get(i).getOfferID() == acceptId) {
				LoggingUtil.trace("acceptOffer(); - found matching ID");

				accepted = true;

				Car offerCar = MasterOfferList.getOfferlist().get(i).getOfferCar();

				Customer buyer = null;

				for (Customer c : CustomerBase.getCustomerlist()) {
					if (c.getUsername().equals(MasterOfferList.getOfferlist().get(i).getOfferer().getUsername())) {
						buyer = c;
					}
				}

				// adding car to customer car list
				buyer.getCarsOwned().add(offerCar);

				// setting buyer new overall balance to past balance + offer balance
				buyer.setBalance(buyer.getBalance() + MasterOfferList.getOfferlist().get(i).getOfferPrice());

				// remove pending offer from buyer where the unique id match
				for (int j = 0; j < buyer.getPendingOffers().size(); j++) {
					if (buyer.getPendingOffers().get(j).getOfferID() == acceptId) {
						buyer.getPendingOffers().remove(j);
					}
				}

				// set making payments to true if not already true
				if (!buyer.isMakingPayments()) {
					buyer.setMakingPayments(true);
				}

				//removing other offers from list
				for (int j = MasterOfferList.getOfferlist().size() - 1; j >= 0; j--) {
					if (MasterOfferList.getOfferlist().get(j).getOfferCar().getCarID() == offerCar.getCarID()) {

						String purchasePrice = MasterOfferList.getOfferlist().get(j).getOfferPrice().toString();
						offerCar.setPurchasedPrice(purchasePrice);

						MasterOfferList.getOfferlist().remove(j);
						
						//accept offer -> change to db // 3 is accepted offer
						offerDAO.updateOffer(MasterOfferList.getOfferlist().get(j), 1);
						
						
					}
				}

				// set forSale false, setting car offer list to null, setting username to care
				offerCar.setForSale(false);
				offerCar.setCarOfferList(null);
				offerCar.setOwnerUsername(buyer.getUsername());

				// remove car from lot
				CarLotServiceImpl clsi = new CarLotServiceImpl();
				clsi.removeCar(offerCar.getCarID());

				// calculate monthly payment
				WebServiceImpl wsi = new WebServiceImpl();
				wsi.calculateMonthlyPayment(buyer);
				
				
				
				//update car changes to database
				carDAO.updateCar(offerCar, buyer);
				//accept offer -> change to db // 3 is accepted offer
				offerDAO.updateOffer(MasterOfferList.getOfferlist().get(i), 3);
				//update customer balance
				customerDAO.updateCustomer(buyer);
				
				

			}

		}

		if (!accepted) {
			System.out.println("ID Not Found. Please Try Again.");
		}

	}

	@Override
	public void rejectOffer() {
		LoggingUtil.trace("EmployeeServiceImpl - rejectOffer() - start");
		System.out.println("-- Reject Offer Screen --");

		Scanner scanner = new Scanner(System.in);
		Integer rejectId;
		boolean rejected = false;

		System.out.println("Enter Offer ID to reject-->");
		while (!scanner.hasNextInt()) {
			System.out.println("Please Enter a Valid Number.");
			scanner.next();
		}
		rejectId = scanner.nextInt();
		scanner.nextLine();

		for (int i = 0; i < MasterOfferList.getOfferlist().size(); i++) {
			if (MasterOfferList.getOfferlist().get(i).getOfferID() == rejectId) {
				rejected = true;
				// getting the customer (reject)
				Customer rejectCustomer = MasterOfferList.getOfferlist().get(i).getOfferer();

				// remove pending offer from buyer where the unique id match
				for (int j = 0; j < rejectCustomer.getPendingOffers().size(); j++) {
					if (rejectCustomer.getPendingOffers().get(j).getOfferID() == rejectId) {
						rejectCustomer.getPendingOffers().remove(j);
					}
				}

				// removing from the master list as well by ID, keep other offers
				MasterOfferList.getOfferlist().remove(i);

			}
		}

		if (!rejected) {
			System.out.println("ID Not Found. Please Try Again.");
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

			System.out.println("Enter '1': To view the entire boat offer list");
			System.out.println("Enter '2': To ACCEPT a boat offer");
			System.out.println("Enter '3': To REJECT a boat offer");
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
