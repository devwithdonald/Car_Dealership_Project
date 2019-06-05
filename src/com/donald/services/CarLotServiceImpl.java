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
		System.out.println("Please enter the type: ");
		price = scanner.nextLine();

		System.out.println("Please enter the price: ");
		carType = scanner.nextLine();

		// adding car
		CarLot.getCarlot().add(new Car("dealer", price, carType, true));

		// success message
		// prints out last car in the list
		// last car in list == last car added
		System.out.println("Added " + CarLot.getCarlot().get(CarLot.getCarlot().size() - 1) + " to the car lot!");

	}

	@Override
	public void removeCar(Car car) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeCar(Car car) {
		// TODO Auto-generated method stub

	}

}
