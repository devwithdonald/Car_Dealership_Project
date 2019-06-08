package com.donald.dao;

import java.util.List;

import com.donald.users.Car;


public interface CarLotDAO {
	
	public void saveCarLot(List<Car> carLot);
	
	public List<Car> loadCarLot();
}
