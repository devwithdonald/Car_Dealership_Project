package com.donald.sqldao;

import java.util.List;

import com.donald.users.Car;
import com.donald.users.Customer;

public interface CarSQLDAO {

	public int insertCar(Car car);

	public void updateCar(Car car, Customer customer);
	
	public void updateCarOnRemoval(Car car);

	public List<Car> getAllCars();

	public List<Car> getCarsByCustomerId(int id);
	
	public Car getCarById(int id);
	

}
