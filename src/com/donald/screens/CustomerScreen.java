package com.donald.screens;

import java.util.Scanner;

import com.donald.services.CarLotServiceImpl;
import com.donald.services.EmployeeServiceImpl;
import com.donald.services.WebServiceImpl;
import com.donald.users.Employee;
import com.donald.users.MasterCustomerLoginList;

public class CustomerScreen implements UserScreen {

	@Override
	public boolean display() {
		// TODO Auto-generated method stub

		if (!customerAccess()) {
			return false;
		}

		boolean exitInput = true;
		String input = "";
			
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.println("\nWelcome! What would you like to do today?");

			System.out.println("Enter '1': View all cars on the lot");
			System.out.println("Enter '2': To make an offer on a car");
			System.out.println("Enter '3': View cars that you own");
			System.out.println("Enter '4': View remaining payments on an owned car");
			System.out.println("Enter '0': Exit!");

			input = scanner.nextLine();
			
			CarLotServiceImpl clsi = new CarLotServiceImpl();
		
			// call stuff
			switch (input) {
			case "1":
				clsi.viewCarLot();
				exitInput = false;
				break;
			case "2":

				exitInput = false;
				break;
			case "3":

				exitInput = false;
				break;
			case "4":

				exitInput = false;
				break;
			case "0":
				exitInput = true;
				System.out.println("Thank you, have a good day!\n");
				break;
			}
		}while (!exitInput);

		return false;
	}

	@Override
	public boolean loginVerification() {
		System.out.println("-- Login Verification -- ");

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

		do {

			Scanner scanner = new Scanner(System.in);

			System.out.println("enter new username-->");
			username = scanner.nextLine();

			if (MasterCustomerLoginList.getCustomerloginmap().containsKey(username)) {
				System.out.println("this username is already taken");

			} else {
				System.out.println("enter new password-->");
				password = scanner.nextLine();

				MasterCustomerLoginList.getCustomerloginmap().put(username, password);
				System.out.println("Success! '" + username + "' is now a registered user!");
				
				//TODO create new customer object and add them to the list of master customer list
				
				
				exitInput = true;
			}

		} while (!exitInput);


		
		
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
