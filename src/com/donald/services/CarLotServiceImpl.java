package com.donald.services;

import java.util.Scanner;

import com.donald.users.Car;
import com.donald.users.CarLot;

public class CarLotServiceImpl implements CarLotServiceInt {

	@Override
	public void viewCarLot() {
		System.out.println("-- in view car lot --");

		for (int i = 0; i < CarLot.getCarlot().size(); i++) {
			System.out.println("Car " + i + ": " + CarLot.getCarlot().get(i));
		}

	}

	@Override
	public void addCar() {
		System.out.println("-- Enter a new car screen --");

		// TODO CREATE A CREATECAR METHOD
		String price = "";
		String carType = "";

		Scanner scanner = new Scanner(System.in);

		// TODO will need logic
		// ownerUsername & forSale are default set
		System.out.println("Please enter the car type: ");
		price = scanner.nextLine();

		System.out.println("Please enter the car price: ");
		carType = scanner.nextLine();

		// adding car
		CarLot.getCarlot().add(new Car("dealer", price, carType, true));

		// success message
		// prints out last car in the list
		// last car in list == last car added
		System.out.println("Added " + CarLot.getCarlot().get(CarLot.getCarlot().size() - 1) + " to the car lot!");

	}

	@Override
	public void removeCar() {
		// TODO Auto-generated method stub
		
		//give option to see car list OR just input the car
		
		boolean exitInput = false;
		String input = "";
	
		do {
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Enter '1': To view the car the list");
			System.out.println("Enter '2': To input the carID you would like to remove");
			System.out.println("Enter '0': Exit!");
			
			input = scanner.nextLine();
			
			//loop until exit (to remove more than 1 car)
			if(input.equals("1")) {
				//show car list
				viewCarLot();
				//reset input
				input = "";
			} else if (input.equals("2")) {
				// allow them to input the car id to remove!
				
				//get the car list and remove item depending on the id!
				//TODO should be its own method
				System.out.println("Enter Car ID -->");
				input = scanner.nextLine();
				
				//parse to int
				int intInput = Integer.parseInt(input);
				
				for (int i = 0; i < CarLot.getCarlot().size(); i++) {
					//TODO FIX THIS
					if(CarLot.getCarlot().get(i).getCarID() == intInput) {
						//remove functionality goes here
						
					}
				}
				
				
				//reset input
				input = "";
			} else if (input.equals("0")) {
				exitInput = true;
			} 
			
		}while (!exitInput);
	
	}

}
