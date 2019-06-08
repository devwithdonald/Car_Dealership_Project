package com.donald.dao;

import java.util.List;

import com.donald.users.CarId;

public interface CarIdDAO {

	public void saveCarId(List<CarId> carId);

	public List<CarId> loadCarId();
}
