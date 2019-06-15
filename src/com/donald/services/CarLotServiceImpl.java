package com.donald.services;

import java.util.Scanner;

import com.donald.sqldao.CarPostgresDAOImpl;
import com.donald.users.Car;
import com.donald.users.CarId;
import com.donald.users.CarIdCounter;
import com.donald.users.CarLot;
import com.donald.users.MasterOfferList;
import com.donald.util.LoggingUtil;

public class CarLotServiceImpl implements CarLotServiceInt {
	
	private static CarPostgresDAOImpl carDAO;
	

	@Override
	public void viewCarLot() {
		LoggingUtil.trace("CarLotServiceImpl - viewCarLot(); - start");
		System.out.println("-- In View Boat Lot --");

		if (CarLot.getCarlot().size() == 0) {
			LoggingUtil.warn("CarLotServiceImpl - car lot is empty");
			System.out.println("Boat Lot is Empty!");
		} else {
			LoggingUtil.warn("CarLotServiceImpl - car lot is NOT empty");
			for (int i = 0; i < CarLot.getCarlot().size(); i++) {
				System.out.println(CarLot.getCarlot().get(i));
			}

		}

	}

	@Override
	public void viewCarLotLimited() {
		LoggingUtil.trace("CarLotServiceImpl - viewCarLotLimited(); - start");
		System.out.println("-- In View Boat Lot --");

		if (CarLot.getCarlot().size() == 0) {
			LoggingUtil.warn("CarLotServiceImpl - car lot is empty");
			System.out.println("Boat Lot is Empty!");
		} else {
			LoggingUtil.warn("CarLotServiceImpl - car lot is NOT empty");
			for (int i = 0; i < CarLot.getCarlot().size(); i++) {
				System.out.println("ID -> " + CarLot.getCarlot().get(i).getCarID() + " Type -> "
						+ CarLot.getCarlot().get(i).getCarType() + " Price -> " + CarLot.getCarlot().get(i).getPrice());
			}

		}

	}

	@Override
	public void addCar() {
		LoggingUtil.trace("CarLotServiceImpl - addCar(); - start");

		System.out.println("-- Add New Boat Screen --");

		
		//add car in local system
		Car car = createCar();
		// Add car to db // returns newly generated car ID
		int newId = carDAO.insertCar(car);
		car.setCarID(newId);
		// Add car to lot locally
		CarLot.getCarlot().add(car);
		
		// success message
		System.out.println("Added " + CarLot.getCarlot().get(CarLot.getCarlot().size() - 1) + " to the boat lot!");

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

		//take id out?
		//Car newCar = new Car("dealer", price, carType, true, CarIdCounter.getCaridcounter().size());
		Car newCar = new Car("dealer", price, carType, true);
		
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

				if (CarLot.getCarlot().size() == 0) {
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

		for (int i = 0; i < CarLot.getCarlot().size(); i++) {

			if (CarLot.getCarlot().get(i).getCarID() == carId) {

				System.out.println("Removing Boat: " + CarLot.getCarlot().get(i).toString());
				CarLot.getCarlot().remove(i);
				carRemoveCheck = true;

			}
		}

		removeCarFromOfferList(carId);

		if (!carRemoveCheck) {
			System.out.println("Boat ID Not Found. No Boat Removed.");
		}

	}

	@Override
	public void removeCarFromOfferList(Integer carId) {

		for (int i = MasterOfferList.getOfferlist().size() - 1; i >= 0; i--) {
			if (MasterOfferList.getOfferlist().get(i).getOfferCar().getCarID() == carId) {
				MasterOfferList.getOfferlist().remove(i);
			}
		}

	}

	@Override
	public Car matchCarId(Integer carId) {

		Car car = null;

		for (int i = 0; i < CarLot.getCarlot().size(); i++) {
			if (CarLot.getCarlot().get(i).getCarID() == carId) {
				car = CarLot.getCarlot().get(i);
			}
		}

		return car;

	}

}
