package com.donald.services;

import java.util.Scanner;

import com.donald.dao.CarIdSerializeDAO;
import com.donald.dao.CarLotSerializeDAO;
import com.donald.users.Car;
import com.donald.users.CarId;
import com.donald.users.CarIdCounter;
import com.donald.users.CarLot;

public class CarLotServiceImpl implements CarLotServiceInt {

	// load/save car lot
	CarLotSerializeDAO carLotData = new CarLotSerializeDAO();

	@Override
	public void viewCarLot() {
		System.out.println("-- in view car lot --");

		// remove data so no duplicates
		CarLot.getCarlot().clear();
		// load data
		if (carLotData.loadCarLot() != null) {
			CarLot.getCarlot().addAll(carLotData.loadCarLot());
		}

		for (int i = 0; i < CarLot.getCarlot().size(); i++) {
			System.out.println("Car " + i + ": " + CarLot.getCarlot().get(i));
		}

	}

	@Override
	public void addCar() {
		System.out.println("-- Enter a new car screen --");

		
		CarLot.getCarlot().clear();

		if (carLotData.loadCarLot() != null) {
			CarLot.getCarlot().addAll(carLotData.loadCarLot());
		}

		// TODO CREATE A CREATECAR METHOD
		String price = "";
		String carType = "";

		Scanner scanner = new Scanner(System.in);

		// TODO will need logic
		// ownerUsername & forSale are default set
		System.out.println("Please enter the car type: ");
		carType = scanner.nextLine();

		System.out.println("Please enter the car price: ");
		price = scanner.nextLine();

		// TODO MAKE OWN METHOD
		CarIdSerializeDAO cis = new CarIdSerializeDAO();

		// clear id list
		CarIdCounter.getCaridcounter().clear();

		// load id list
		if (cis.loadCarId() != null) {
			CarIdCounter.getCaridcounter().addAll(cis.loadCarId());
		}

		// add new couny
		CarId carId = new CarId(1);
		CarIdCounter.getCaridcounter().add(carId);

		// adding car
		CarLot.getCarlot().add(new Car("dealer", price, carType, true, CarIdCounter.getCaridcounter().size()));

		// save
		cis.saveCarId(CarIdCounter.getCaridcounter());

		// saving carLot data
		carLotData.saveCarLot(CarLot.getCarlot());

		// success message
		// prints out last car in the list
		// last car in list == last car added
		System.out.println("Added " + CarLot.getCarlot().get(CarLot.getCarlot().size() - 1) + " to the car lot!");

	}

	@Override
	public void removeCar() {
		System.out.println("-- Remove a car screen --");

		// give option to see car list OR just input the car

		boolean exitInput = false;
		boolean carRemove = false;
		String input = "";

		do {

			// remove data so no duplicates
			CarLot.getCarlot().clear();
			// load data
			CarLot.getCarlot().addAll(carLotData.loadCarLot());

			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter '1': To view the car the list");
			System.out.println("Enter '2': To input the carID you would like to remove");
			System.out.println("Enter '0': Exit!");

			input = scanner.nextLine();

			// loop until exit (to remove more than 1 car)
			if (input.equals("1")) {
				// show car list if not empty
				if (CarLot.getCarlot().size() == 0) {
					System.out.println("Car list is empty!");
				} else {
					viewCarLot();
				}

				// reset input
				input = "";
			} else if (input.equals("2")) {
				// allow them to input the car id to remove!

				// get the car list and remove item depending on the id!
				// TODO should be its own method
				System.out.println("Enter Car ID -->");
				input = scanner.nextLine();

				// parse to int
				// TODO need to ensure int
				int intInput = Integer.parseInt(input);

				if (CarLot.getCarlot().size() == 0) {
					System.out.println("Car list is empty!");
				} else {
					for (int i = 0; i < CarLot.getCarlot().size(); i++) {
						// Get the car's id from the car lot
						if (CarLot.getCarlot().get(i).getCarID() == intInput) {
							// remove functionality goes here
							System.out.println("Removing Car: " + CarLot.getCarlot().get(i).toString());
							CarLot.getCarlot().remove(i);
							carRemove = true;

							// saving carLot data with
							carLotData.saveCarLot(CarLot.getCarlot());
						}
					}
				}

				if (!carRemove) {
					System.out.println("carId not found. No car removed.");
				}

				// reset input
				input = "";
				carRemove = false;
			} else if (input.equals("0")) {
				exitInput = true;
			}

		} while (!exitInput);

	}

}
