package com.donald.services;

import java.util.Scanner;

import com.donald.dao.CarIdSerializeDAO;
import com.donald.dao.CarLotSerializeDAO;
import com.donald.users.Car;
import com.donald.users.CarId;
import com.donald.users.CarIdCounter;
import com.donald.users.CarLot;
import com.donald.util.LoggingUtil;

public class CarLotServiceImpl implements CarLotServiceInt {

	@Override
	public void viewCarLot() {
		LoggingUtil.trace("CarLotServiceImpl - viewCarLot(); - start");
		System.out.println("-- in view car lot --");

		if (CarLot.getCarlot().size() == 0) {
			LoggingUtil.warn("CarLotServiceImpl - car lot is empty");
			System.out.println("Car Lot is Empty!");
		} else {
			LoggingUtil.warn("CarLotServiceImpl - car lot is NOT empty");
			for (int i = 0; i < CarLot.getCarlot().size(); i++) {
				System.out.println("Car " + i + ": " + CarLot.getCarlot().get(i));
			}

		}

	}

	@Override
	public void addCar() {
		LoggingUtil.trace("CarLotServiceImpl - addCar(); - start");

		System.out.println("-- Add New Car Screen --");

		// Add car to lot
		CarLot.getCarlot().add(createCar());

		// success message
		System.out.println("Added " + CarLot.getCarlot().get(CarLot.getCarlot().size() - 1) + " to the car lot!");

	}

	// TODO JUNIT TEST
	@Override
	public Car createCar() {
		LoggingUtil.trace("CarLotServiceImpl - createCar(); - start");

		String price = "";
		String carType = "";

		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter the car type: ");
		carType = scanner.nextLine();

		System.out.println("Please enter the car price: ");
		price = scanner.nextLine();

		// add new count
		CarId carId = new CarId(1);
		CarIdCounter.getCaridcounter().add(carId);

		Car newCar = new Car("dealer", price, carType, true, CarIdCounter.getCaridcounter().size());

		return newCar;

	}

	@Override
	public void removeCarMenu() {
		LoggingUtil.trace("CarLotServiceImpl - removeCarMenu(); - start");
		System.out.println("-- Remove a car screen --");

		boolean exitInput = false;
		String input = "";

		do {
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter '1': To view the car the list");
			System.out.println("Enter '2': To input the carID you would like to remove");
			System.out.println("Enter '0': Exit!");

			input = scanner.nextLine();

			if (input.equals("1")) {
				viewCarLot();
				// reset input
				input = "";

			} else if (input.equals("2")) {

				if (CarLot.getCarlot().size() == 0) {
					System.out.println("Unable to Remove Car -> Car Lot is Empty");
				} else {
					removeCar();
					input = "";
				}

			} else if (input.equals("0")) {
				exitInput = true;
			}

		} while (!exitInput);

	}
	
	

	@Override
	public void removeCar() {
		LoggingUtil.trace("CarLotServiceImpl - removeCar(); - start");

		System.out.println("Enter Car ID -->");

		Scanner scanner = new Scanner(System.in);
		String carID = "";
		Boolean carRemoveCheck = false;
		
		carID = scanner.nextLine();
		// TODO FIX
		int intCarID = Integer.parseInt(carID);

		
		for (int i = 0; i < CarLot.getCarlot().size(); i++) {

			if (CarLot.getCarlot().get(i).getCarID() == intCarID) {
				
				System.out.println("Removing Car: " + CarLot.getCarlot().get(i).toString());
				CarLot.getCarlot().remove(i);
				carRemoveCheck = true;

			}
		}

		if (!carRemoveCheck) {
			System.out.println("CarId Not Found. No Car Removed.");
		}

	}
	
	//TODO JUNIT
	@Override
	public Car matchCarId(Integer carId) {
		
		Car car = null;
		
		for (int i = 0; i < CarLot.getCarlot().size(); i++) {
			if (CarLot.getCarlot().get(i).getCarID() == carId) {
				//return the car
				car = CarLot.getCarlot().get(i);
			}
		}
		return car;
		
	}

}
