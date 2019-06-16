package com.donald.services;

import java.util.Scanner;

import com.donald.sqldao.CarPostgresDAOImpl;
import com.donald.sqldao.OfferPostgresDAOImpl;
import com.donald.users.Car;
import com.donald.users.CarId;
import com.donald.users.CarIdCounter;
import com.donald.users.CarLot;
import com.donald.users.MasterOfferList;
import com.donald.util.LoggingUtil;

public class CarLotServiceImpl implements CarLotServiceInt {

	private static CarPostgresDAOImpl carDAO = new CarPostgresDAOImpl();
	private static OfferPostgresDAOImpl offerDAO = new OfferPostgresDAOImpl();

	@Override
	public void viewCarLot() {
		LoggingUtil.trace("CarLotServiceImpl - viewCarLot(); - start");
		System.out.println("-- In View Boat Lot --");

		if (carDAO.getAllCars().size() == 0) {
			LoggingUtil.warn("CarLotServiceImpl - car lot is empty");
			System.out.println("Boat Lot is Empty!");
		} else {
			for (Car car : carDAO.getAllCars()) {
				System.out.println(car);
			}
		}

	}


	@Override
	public void addCar() {
		LoggingUtil.trace("CarLotServiceImpl - addCar(); - start");

		System.out.println("-- Add New Boat Screen --");

		// add car in local system
		Car car = createCar();

		LoggingUtil.debug("car in add car method -> " + car);
		// Add car to db // returns newly generated car ID
		int newId = carDAO.insertCar(car);

		// get car by id
		System.out.println(carDAO.getCarById(newId).getCarType() + " added!" + " Id Number: " + newId);

	}

	@Override
	public Car createCar() {
		LoggingUtil.trace("CarLotServiceImpl - createCar(); - start");

		String price = "";
		String carType = "";

		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter the boat type: ");
		carType = scanner.nextLine();

		System.out.println("Please enter the boat price: ");
		price = scanner.nextLine();

		// add new count
		CarId carId = new CarId(1);
		CarIdCounter.getCaridcounter().add(carId);

		Car newCar = new Car("dealer", price, carType, true);

		LoggingUtil.debug("New Car Created ->" + newCar);

		return newCar;

	}

	@Override
	public void removeCarMenu() {
		LoggingUtil.trace("CarLotServiceImpl - removeCarMenu(); - start");
		System.out.println("-- Remove a boat screen --");

		boolean exitInput = false;
		String input = "";

		do {
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter '1': To view the boat the list");
			System.out.println("Enter '2': To input the boat ID you would like to remove");
			System.out.println("Enter '0': Exit!");

			input = scanner.nextLine();

			if (input.equals("1")) {
				viewCarLot();
				// reset input
				input = "";

			} else if (input.equals("2")) {

				if (carDAO.getAllCars().size() == 0) {
					System.out.println("Unable to Remove Boat -> Boat Lot is Empty");
				} else {

					// get car id
					removeCar(getCarId());
					input = "";
				}

			} else if (input.equals("0")) {
				exitInput = true;
			}

		} while (!exitInput);

	}

	@Override
	public Integer getCarId() {
		LoggingUtil.trace("CarLotServiceImpl - getCarId(); - start");

		Integer carID;

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter Boat ID -->");
		while (!scanner.hasNextInt()) {
			System.out.println("Please Enter a Valid Number.");
			scanner.next();
		}
		carID = scanner.nextInt();
		scanner.nextLine();

		return carID;
	}

	@Override
	public void removeCar(Integer carId) {
		LoggingUtil.trace("CarLotServiceImpl - removeCar(); - start");

		Boolean carRemoveCheck = false;

		for (int i = 0; i < carDAO.getAllCars().size(); i++) {

			if (carDAO.getAllCars().get(i).getCarID() == carId) {

				// change status car for FOR SALE
				carDAO.updateCarOnRemoval(carDAO.getCarById(carId));
				// change status of car for OFFER
				offerDAO.updateOfferOnCarRemoval(carId);

				carRemoveCheck = true;

			}
		}

		if (!carRemoveCheck) {
			System.out.println("Boat ID Not Found. No Boat Removed.");
		}

	}

}
