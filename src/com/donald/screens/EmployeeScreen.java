package com.donald.screens;

import java.util.Scanner;

import com.donald.services.CarLotServiceImpl;
import com.donald.users.Employee;

public class EmployeeScreen extends Screen {

	// this method should call other methods that employee can do?
	// boolean should return whether the program keeps runnning
	public boolean employeeOption() {

		// need to pass the login verification (if they dont return false)
		// if false kick them out back to intial screen
		if (!loginVerification()) {
			return false;
		}

		// if they get passed the login verification
		// show them the option they have as an employee
		boolean exitInput = true;
		String input = "";

		do {
			Scanner scanner = new Scanner(System.in);
			System.out.println("\nWelcome! What would you like to do today?");

			System.out.println("Enter '1': To add a car to the car lot. ");
			System.out.println("Enter '2': To accept or reject offers.");
			System.out.println("Enter '3': To remove a car from the car lot");
			System.out.println("Enter '4': View all payments");
			System.out.println("Enter '0': Exit!");

			input = scanner.nextLine();

			// call stuff
			switch (input) {
			case "1":
				CarLotServiceImpl cls = new CarLotServiceImpl();
				cls.addCar();
				exitInput = false;
				break;
			case "2":
				break;
			case "3":
				
				break;
			case "4":
				break;
			case "0":
				exitInput = true;
				System.out.println("Thank you, have a good day!\n");
				break;
			}

		} while (!exitInput);

		return false;
	}

	public boolean loginVerification() {

		int counter = 0;

		String username = "";
		boolean verifiedUsername = false;
		String password = "";
		boolean verifiedPassword = false;

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

			if (username.equals(Employee.getEmployee().getUSERNAME())) {
				verifiedUsername = true;
				counter++;
			}

			// password validation
			System.out.println("enter password --> ");
			password = scanner.nextLine();

			if (password.equals(Employee.getEmployee().getUSERNAME())) {
				verifiedPassword = true;
				counter++;
			}

		} while (!verifiedUsername && !verifiedPassword);

		if (counter == 2) {
			// verified let them in!
			return true;
		} else {
			// not verified kick them out!
			return false;
		}

	}

}
