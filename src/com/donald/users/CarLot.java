package com.donald.users;

import java.util.ArrayList;
import java.util.List;

public class CarLot {

	// only one car lot
	private static final List<Car> carLot = new ArrayList<>();

	public static List<Car> getCarlot() {
		return carLot;
	}

}
