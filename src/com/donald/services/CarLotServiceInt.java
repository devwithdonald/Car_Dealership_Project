package com.donald.services;

import com.donald.users.Car;

public interface CarLotServiceInt {

	public void viewCarLot();
	
	public void viewCarLotLimited();
	
	public void addCar();
	
	public Car createCar();
	
	public void removeCarMenu();
	
	public void removeCar(Integer carId);
	
	public Car matchCarId(Integer carId);
	
}
