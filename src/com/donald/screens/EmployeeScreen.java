package com.donald.screens;

import java.util.Scanner;

import com.donald.users.Employee;

public class EmployeeScreen extends Screen {

	// this method should call other methods that employee can do?
	// boolean should return whether the program keeps runnning
	public boolean employeeOption() {

		// need to pass the login verification (if they dont return false)
		//if false kick them out back to intial screen
		if (!loginVerification()) {
			return false;
		}
		
		return false;
	}

	public boolean loginVerification() {

		Scanner scanner = new Scanner(System.in);

		int counter = 0;

		String username = "";
		boolean verifiedUsername = false;
		String password = "";
		boolean verifiedPassword = false;

		do {
			counter = 0;

			// username validation
			System.out.println("Enter (b) to return to the initial screen. \n");

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

		scanner.close();

		if (counter == 2) {
			// verified let them in!
			return true;
		} else {
			// not verified kick them out!
			return false;
		}

	}

}
