package com.donald.screens;

import java.util.Scanner;

import com.donald.dao.CarLotSerializeDAO;
import com.donald.dao.CustomerListSerializeDAO;
import com.donald.dao.LoginListSerializeDAO;
import com.donald.dao.OfferSerializeDAO;
import com.donald.services.CarLotServiceImpl;
import com.donald.services.CustomerServiceImpl;
import com.donald.services.EmployeeServiceImpl;
import com.donald.services.WebServiceImpl;
import com.donald.users.CarLot;
import com.donald.users.Customer;
import com.donald.users.CustomerBase;
import com.donald.users.Employee;
import com.donald.users.MasterCustomerLoginList;
import com.donald.users.MasterOfferList;
import com.donald.util.LoggingUtil;

public class CustomerScreen implements UserScreen {

	Customer loggedInCustomer;

	//TODO JUNIT TEST
	@Override
	public boolean display() {
		LoggingUtil.trace("CustomerScreen - display() - start");

		if (!customerAccessMenu()) {
			return false;
		}

		boolean exitInput = true;
		String input = "";

		do {
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("\nWelcome " + loggedInCustomer.getUsername() + "! What would you like to do today?");
			System.out.println("Enter '1': View all cars on the lot");
			System.out.println("Enter '2': To make an offer on a car");
			System.out.println("Enter '3': View cars that you own");
			System.out.println("Enter '4': View remaining payments on an owned car");
			System.out.println("Enter '0': Exit!");
			input = scanner.nextLine();

			CarLotServiceImpl clsi = new CarLotServiceImpl();
			CustomerServiceImpl csi = new CustomerServiceImpl();

			
			switch (input) {
			case "1":
				LoggingUtil.trace("CustomerScreen - display() - calling viewCarLot();");
				clsi.viewCarLotLimited();
				exitInput = false;
				break;
			case "2":
				LoggingUtil.trace("CustomerScreen - display() - calling makeOffer(loggedInCustomer);");
				csi.makeOffer(loggedInCustomer);
				exitInput = false;
				break;
			case "3":
				LoggingUtil.trace("CustomerScreen - display() - calling viewOwnedCars(loggedInCustomer);");
				csi.viewOwnedCars(loggedInCustomer);
				exitInput = false;
				break;
			case "4":
				LoggingUtil.trace("CustomerScreen - display() - calling viewRemainingPayments(loggedInCustomer);");
				csi.viewRemainingPayments(loggedInCustomer);
				exitInput = false;
				break;
			case "0":
				LoggingUtil.trace("CustomerScreen - display() - exiting");
				exitInput = true;
				System.out.println("Thank You, Have a Good Day!\n");
				break;
			}
		} while (!exitInput);

		return false;
	}

	// TODO JUNIT TEST
	@Override
	public boolean loginVerification() {
		LoggingUtil.trace("CustomerScreen - loginVerification - start");
		System.out.println("-- Login Verification -- ");

		int counter = 0;

		String username = "";
		boolean verifiedUsername = false;
		String password = "";
		boolean verifiedPassword = true;
		// boolean exitInput = false;

		do {
			LoggingUtil.trace("LoginVerification(); - do loop - start");

			Scanner scanner = new Scanner(System.in);
			counter = 0;

			// username validation
			System.out.println("Enter (b) to return to the initial screen.");
			System.out.println("enter username --> ");
			username = scanner.nextLine();

			if (username.equals("b")) {
				break;
			}
			if (MasterCustomerLoginList.getCustomerloginmap().containsKey(username)) {
				verifiedUsername = true;
				counter++;
			}
			// password validation
			System.out.println("enter password --> ");
			password = scanner.nextLine();

			if (counter == 1) {
				if (MasterCustomerLoginList.getCustomerloginmap().get(username).equals(password)) {
					verifiedPassword = true;
					counter++;
				}
			} else {
				LoggingUtil.debug("LoginVerification(); - do loop - username not found");
				System.out.println("Username Not Found!");
			}
			// after failed password attempt
			if (counter == 1) {
				LoggingUtil.debug("LoginVerification(); - do loop - password not wrong");
				System.out.println("Wrong Password");
			}
		} while (!verifiedUsername && !verifiedPassword);

		if (counter == 2) {
			// verified let them in!
			LoggingUtil.trace("LoginVerification(); - do loop - login verified");
			customerLoginMatch(username);
			return true;
		} else {
			LoggingUtil.warn("failed login attempt");
			return false;
		}
	}

	public void customerLoginMatch(String username) {

		for (int i = 0; i < CustomerBase.getCustomerlist().size(); i++) {
			if (CustomerBase.getCustomerlist().get(i).getUsername().equals(username)) {
				LoggingUtil.debug("customerLoginMatch(); - getting logged in customer");
				loggedInCustomer = CustomerBase.getCustomerlist().get(i);
			}
		}
	}

	public void register() {
		LoggingUtil.trace("CustomerScreen - register() - start");
		System.out.println("-- Customer Registration -- ");

		String username = "";
		String password = "";
		boolean exitInput = false;

		do {
			LoggingUtil.trace("CustomerScreen - do loop - start");

			Scanner scanner = new Scanner(System.in);

			System.out.println("enter new username-->");
			username = scanner.nextLine();

			if (MasterCustomerLoginList.getCustomerloginmap().containsKey(username)) {
				System.out.println("This Username is Already Taken");
			} else {
				System.out.println("Enter New Password-->");
				password = scanner.nextLine();
			

				System.out.println("Success! '" + username + "' is Now a Registered User!");

				CustomerBase.getCustomerlist().add(new Customer(username, password));
				MasterCustomerLoginList.getCustomerloginmap().put(username, password);

				exitInput = true;
			}

		} while (!exitInput);

	}

	// TODO JUNIT TEST
	public boolean customerAccessMenu() {
		LoggingUtil.trace("CustomerScreen - customerAccessMenu(); - start");
		boolean exitInput = false;
		boolean returnBool = true;
		String input = "";

		do {
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter '1': Login as existing user");
			System.out.println("Enter '2': Register as new user");
			System.out.println("Enter '0': Exit!");

			input = scanner.nextLine();

			if (input.equals("1")) {
				LoggingUtil.trace("CustomerScreen - customerAccessMenu(); - calling loginVerification()");
				exitInput = loginVerification();
			} else if (input.equals("2")) {
				LoggingUtil.trace("CustomerScreen - customerAccessMenu(); - calling register()");
				register();
			} else if (input.equals("0")) {
				LoggingUtil.trace("CustomerScreen - customerAccessMenu(); - exiting");
				exitInput = true;
				returnBool = false;
			}

		} while (!exitInput);

		return returnBool;

	}

}
