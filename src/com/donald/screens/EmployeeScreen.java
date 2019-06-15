package com.donald.screens;

import java.util.Scanner;

import com.donald.services.CarLotServiceImpl;
import com.donald.services.EmployeeServiceImpl;
import com.donald.services.WebServiceImpl;
import com.donald.users.Employee;
import com.donald.util.LoggingUtil;

public class EmployeeScreen implements UserScreen{


	@Override
	public boolean display() {
		LoggingUtil.trace("Employee Screen - display(); - start");

		// if false kick back to initial screen
		if (!loginVerification()) {
			LoggingUtil.trace("false employee login verification");
			return false;
		}

		// if they get passed the login verification
		// show them the option they have as an employee
		boolean exitInput = true;
		String input = "";

		do {
					
			LoggingUtil.trace("do loop - EmployeeScreen - start");
			
			CarLotServiceImpl cls = new CarLotServiceImpl();
			WebServiceImpl wsi = new WebServiceImpl();
			EmployeeServiceImpl esi = new EmployeeServiceImpl();
			

			
			Scanner scanner = new Scanner(System.in);
			System.out.println("\nWelcome! What would you like to do today?");

			System.out.println("Enter '1': To add a boat to the boat lot. ");
			System.out.println("Enter '2': To accept or reject offers.");
			System.out.println("Enter '3': To remove a boat from the boat lot");
			System.out.println("Enter '4': View all payments");
			System.out.println("Enter '0': Exit!");

			input = scanner.nextLine();
			
			switch (input) {
			case "1":
				LoggingUtil.trace("do loop - employee menu - calling addCar();");
				cls.addCar();
				exitInput = false;
				break;
			case "2": 
				LoggingUtil.trace("do loop - employee menu  - calling addCar();");
				esi.offerDecisionMenu();
				exitInput = false;
				break;
			case "3":
				LoggingUtil.trace("do loop - employee menu  - calling removeCar();");
				cls.removeCarMenu();
				exitInput = false;
				break;
			case "4":
				LoggingUtil.trace("do loop - employee menu  - calling viewAllPayments();");
				wsi.viewAllPayments();
				exitInput = false;
				break;
			case "0":
				LoggingUtil.trace("do loop - employee menu  - exiting");
				exitInput = true;
				System.out.println("Thank you, have a good day!\n");
				break;
			}

		} while (!exitInput);

		return false;
	}
	
	

	@Override
	public boolean loginVerification() {
		LoggingUtil.trace("employee loginVerification(); - start");
		
		int counter = 0;

		String username = "";
		boolean verifiedUsername = false;
		String password = "";
		boolean verifiedPassword = false;

		do {

			Scanner scanner = new Scanner(System.in);
			counter = 0;

			// username validation
			System.out.println("Enter (0) to return to the initial screen.");

			System.out.println("enter username --> ");
			username = scanner.nextLine();

			if (username.equals("0")) {
				break;
			}

			if (username.equals(Employee.getEmployee().getUSERNAME())) {
				LoggingUtil.trace("employee username correct");
				verifiedUsername = true;
				counter++;
			}

			// password validation
			System.out.println("enter password --> ");
			password = scanner.nextLine();

			if (password.equals(Employee.getEmployee().getPASSWORD())) {
				LoggingUtil.trace("employee password correct");
				verifiedPassword = true;
				counter++;
			}

		} while (!verifiedUsername && !verifiedPassword);

		if (counter == 2) {
			LoggingUtil.trace("employee login failed");
			return true;
		} else {
			LoggingUtil.trace("employee login passed");
			return false;
		}

	}

}
