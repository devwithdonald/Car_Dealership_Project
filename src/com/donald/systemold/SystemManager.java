package com.donald.systemold;

import java.util.Scanner;

public class SystemManager {

	public void run() {
		
		//
		loginLanding(start());
		
		//if they say employee create employee?
		
		//need a way to verify the login 

	}

	// ask user who they are and assign them the correct login page
	public String start() {

		boolean validInput = false;
		String input = "";
		Scanner scanner = new Scanner(System.in);

		System.out.println("Hello and welcome.");

		// scanner
		do {
			System.out.println("Please enter the correct identifier. Employee (E) or Customer (C)");

			input = scanner.nextLine();

			if (input.equals("E") || input.equals("e")) {
				input = "employee";
				validInput = true;
			} else if (input.equals("C") || input.equals("c")) {
				input = "customer";
				validInput = true;
			}

		} while (!validInput);

		scanner.close();

		return input;
	}

	
	
	public void loginLanding(String name) {

		// if employee or customer than..
		if (name.equals("employee")) {
			// TODO
			// call login for employee
			// show login page for employee
			loginPage();
		}

	}

	public String[] loginPage(){

		String[] loginValidation = new String[2];
		Scanner scanner = new Scanner(System.in);

		String login = "";
		String password = "";

		System.out.println("Login --> ");
		login = scanner.nextLine();
		loginValidation[0] = login;

		System.out.println("Password --> ");
		password = scanner.nextLine();
		loginValidation[1] = password;

		scanner.close();

		return loginValidation;
	}

	// public void employeeLogin();

	// public void customerLogin();

}
