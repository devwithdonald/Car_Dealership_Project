package com.donald.screens;

import java.util.Scanner;

import com.donald.users.Employee;
import com.donald.users.MasterCustomerLoginList;

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
		
		//TODO here 
		
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
			
			if(MasterCustomerLoginList.getCustomerloginmap().containsKey(username)) {
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

			//after failed password attempt
			if(counter == 1) {
				System.out.println("wrong password");
			}
			
			
		}while (!verifiedUsername && !verifiedPassword);
		
		
		//will need to ensure the username password have a match
		//best is probably a master list with MAP
		// if not register to the map then ask again!
		if (counter == 2) {
			// verified let them in!
			return true;
		} else {
			// not verified kick them out!
			return false;
		}
	}
	
	//do loop
	public boolean register() {
		System.out.println("-- Customer Registration -- ");
		
	
		String username = "";
		String password = "";
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("enter new username-->");
		username = scanner.nextLine();
		
		System.out.println("enter new password-->");
		password = scanner.nextLine();
		
		//TODO will need to check if user name is already here
		//add to master customer login map 
		MasterCustomerLoginList.getCustomerloginmap().put(username, password);
		
		
		System.out.println("Success! '" + username + "' is now a registered user!");
		
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
				register();
			} else if (input.equals("0")){
				exitInput = true;
				returnBool = false;
				
			}
			
			
		}while(!exitInput);
		
		return returnBool;
		//call register or loginVerification if not exit
		
	}
	
	
}
