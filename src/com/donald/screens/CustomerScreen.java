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

public class CustomerScreen implements UserScreen {
	
	
	Customer loggedInCustomer;
	//SAVE FILE
	LoginListSerializeDAO loginListData = new LoginListSerializeDAO();
	CustomerListSerializeDAO customerListdata = new CustomerListSerializeDAO();
	OfferSerializeDAO offerListData = new OfferSerializeDAO();
	CarLotSerializeDAO carLotData = new CarLotSerializeDAO();

	@Override
	public boolean display() {
		// TODO Auto-generated method stub

		//LOAD all here?
		

		
		if (!customerAccess()) {
			return false;
		}

		boolean exitInput = true;
		String input = "";

		do {
			//might need to load here?
			
			//load
			MasterOfferList.getOfferlist().clear();

			if (offerListData.loadOfferList() != null) {
				 MasterOfferList.getOfferlist().addAll(offerListData.loadOfferList());
			}
			
			//load
			CustomerBase.getCustomerlist().clear();

			if (customerListdata.loadCustomerList() != null) {
				 CustomerBase.getCustomerlist().addAll(customerListdata.loadCustomerList());
			}
			
			//load
			CarLot.getCarlot().clear();

			if (carLotData.loadCarLot() != null) {
				 CarLot.getCarlot().addAll(carLotData.loadCarLot());
			}
			
			
			
			
			
			
			
			
			System.out.println("In login screen -> car list" + loggedInCustomer.getCarsOwned());
			
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
			
			
			
			
			// call stuff
			switch (input) {
			case "1":
				clsi.viewCarLot();
				exitInput = false;
				break;
			case "2":
				//TODO NOT COMPLETE
				csi.makeOffer(loggedInCustomer);
				exitInput = false;
				break;
			case "3":
				csi.viewOwnedCars(loggedInCustomer);
				exitInput = false;
				break;
			case "4":
				//TODO
				csi.viewRemainingPayments(loggedInCustomer);
				exitInput = false;
				break;
			case "0":
				exitInput = true;
				System.out.println("Thank you, have a good day!\n");
				break;
			}
		} while (!exitInput);

		return false;
	}

	@Override
	public boolean loginVerification() {
		System.out.println("-- Login Verification -- ");
		
		
//		//TODO??
//		//CustomerBase.getCustomerlist().clear();
//		//CustomerBase
//		CustomerBase.getCustomerlist().addAll(customerListdata.loadCustomerList());
//		
//		//MasterCustomerLoginList.getCustomerloginmap().keySet().removeAll()
//		MasterCustomerLoginList.getCustomerloginmap().putAll(loginListData.loadLoginList());
//		
		//TODO gives exception when list is empty but tried to load list
		

		
	//	if(CustomerBase.getCustomerlist().size() != 0) {
//		CustomerBase.getCustomerlist().clear();
		
		if(customerListdata.loadCustomerList() != null && loginListData.loadLoginList() != null) {
			CustomerBase.getCustomerlist().clear();
			CustomerBase.getCustomerlist().addAll(customerListdata.loadCustomerList());
			MasterCustomerLoginList.getCustomerloginmap().putAll(loginListData.loadLoginList());
		}
		
		//MasterCustomerLoginList.getCustomerloginmap().keySet().removeAll()
		
		
		//}
		
		
		
		int counter = 0;
		String username = "";
		boolean verifiedUsername = false;
		String password = "";
		boolean verifiedPassword = true;
		boolean exitInput = false;

		do {
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
				System.out.println("username not found!");
			}

			// after failed password attempt
			if (counter == 1) {
				System.out.println("wrong password");
			}

		} while (!verifiedUsername && !verifiedPassword);

		// will need to ensure the username password have a match
		// best is probably a master list with MAP
		// if not register to the map then ask again!
		if (counter == 2) {
			// verified let them in!
			
			// match the customer to get that customer!
			//all usernames are unique
			
			for(int i = 0; i < CustomerBase.getCustomerlist().size(); i++) {
				if (CustomerBase.getCustomerlist().get(i).getUsername().equals(username)) {
					loggedInCustomer = CustomerBase.getCustomerlist().get(i);
				}
			}
			
			
			
			
			
			// TODO should track id and return id back
			return true;
		} else {
			// not verified kick them out!
			return false;
		}
	}

	// do loop
	public boolean register() {
		System.out.println("-- Customer Registration -- ");

		String username = "";
		String password = "";
		boolean exitInput = false;
		
		//TODO TODO TODO will need to load here instead!
		// TODO
		//TODO
		// TODO
		//if(CustomerBase.getCustomerlist().size() != 0) {
		
		
		if(customerListdata.loadCustomerList() != null && loginListData.loadLoginList() != null) {
			CustomerBase.getCustomerlist().clear();
			CustomerBase.getCustomerlist().addAll(customerListdata.loadCustomerList());
			MasterCustomerLoginList.getCustomerloginmap().putAll(loginListData.loadLoginList());
		}
		

		

		do {

			Scanner scanner = new Scanner(System.in);

			System.out.println("enter new username-->");
			username = scanner.nextLine();

			if (MasterCustomerLoginList.getCustomerloginmap().containsKey(username)) {
				System.out.println("this username is already taken");

			} else {
				System.out.println("enter new password-->");
				password = scanner.nextLine();

				System.out.println("Success! '" + username + "' is now a registered user!");


				// list
				//TODO need to update id
//				if(CustomerBase.getCustomerlist().size == 0) {
//					
//				}
				CustomerBase.getCustomerlist().add(new Customer(username, password));

				// might b okay, this is just to keep track of all logins!
				// only accessing this when need to verify username/passwords
				// match username and password to get customer!
				// ^
				MasterCustomerLoginList.getCustomerloginmap().put(username, password);
				
				
				customerListdata.saveCustomerList(CustomerBase.getCustomerlist());
				loginListData.saveLoginList(MasterCustomerLoginList.getCustomerloginmap());
				

				exitInput = true;
			}

		} while (!exitInput);
		
		//CALL THE METHOD SAVE FILE ON MASTERCUSTOMERLOGINLIST SO IT SAVES THE MASTERCUSTOMERLOGINLIST CLASS
		// saveLoginList(MasterCustomerLoginList);
		//should load 

		return true;
	}

	public boolean customerAccess() {

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
				// call login
				
				
				//TODO if loginVerification returns a customer than turn exitInput true, & return that customer 
				//because they are who is logged in. 
				
				
				exitInput = loginVerification();
			} else if (input.equals("2")) {
				// call register
				register();
			} else if (input.equals("0")) {
				exitInput = true;
				returnBool = false;

			}

		} while (!exitInput);

		return returnBool;
		// call register or loginVerification if not exit

	}

}
