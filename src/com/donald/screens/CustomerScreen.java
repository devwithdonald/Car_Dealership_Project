package com.donald.screens;

import java.util.Scanner;

public class CustomerScreen implements UserScreen{

	@Override
	public boolean display() {
		// TODO Auto-generated method stub
		
		//ask if they need to sign up or login
		if(!customerAccess()) {
			return false;
		}
		
//		if(!loginVerification()) {
//			return false;
//		}
		
		//TODO;
		
		return false;
	}

	@Override
	public boolean loginVerification() {
		System.out.println("-- Login Verification -- ");
		
		//will need to ensure the username password have a match
		//best is probably a master list with MAP
		// if not register to the map then ask again!
		return true;
	}
	
	public boolean register() {
		System.out.println("-- Customer Registration -- ");
		
		//should check if its already in there
		
		String username = "";
		String password = "";
		
		System.out.println("new username-->");
		
		
		System.out.println();
		
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
			
			if(input.equals("1")) {
				//call login
				exitInput = loginVerification();
			} else if (input.equals("2")) {
				//call register
				exitInput = register();
			} else if (input.equals("0")){
				exitInput = true;
				returnBool = false;
				
			}
			
			
		}while(!exitInput);
		
		return returnBool;
		//call register or loginVerification if not exit
		
	}
	
	
}
