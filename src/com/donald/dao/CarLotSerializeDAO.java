package com.donald.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.donald.users.Car;
import com.donald.users.Customer;
import com.donald.util.LoggingUtil;

public class CarLotSerializeDAO implements CarLotDAO {

	@Override
	public void saveCarLot(List<Car> carLot) {

		try {
			FileOutputStream fos = new FileOutputStream("CarLotData");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(carLot);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			LoggingUtil.error("IOException called by CarLotSerializeDAO saveCarLot();");
		}

	}

	@Override
	public List<Car> loadCarLot() {
		List<Car> carLot = null;

		try {
			FileInputStream fis = new FileInputStream("CarLotData");
			ObjectInputStream ois = new ObjectInputStream(fis);

			// loads list
			carLot = (ArrayList) ois.readObject();

			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			LoggingUtil.error("FileNotFoundException called by CarLotSerializeDAO loadCarLot();");
			return null;
		} catch (IOException ioe) {
			LoggingUtil.error("IOException called by CarLotSerializeDAO loadCarLot();");
		} catch (ClassNotFoundException c) {
			LoggingUtil.error("ClassNotFoundException called by CarLotSerializeDAO loadCarLot();");
		}


		return carLot;
	}

}
