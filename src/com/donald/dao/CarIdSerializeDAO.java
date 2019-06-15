package com.donald.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.donald.users.CarId;
import com.donald.users.Customer;
import com.donald.util.LoggingUtil;

public class CarIdSerializeDAO implements CarIdDAO {

	@Override
	public void saveCarId(List<CarId> carId) {

		try {

			
			FileOutputStream fos = new FileOutputStream("CarIdData");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(carId);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			LoggingUtil.error("IOException called by CarIdSerializeDAO saveCarID();");
		}

	}

	@Override
	public List<CarId> loadCarId() {

		List<CarId> carIdList = null;

		try {
			FileInputStream fis = new FileInputStream("CarIdData");
			ObjectInputStream ois = new ObjectInputStream(fis);

			// loads list
			carIdList = (ArrayList) ois.readObject();

			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			LoggingUtil.error("FileNotFoundException called by CarIdSerializeDAO loadCarID();");

			return null;
		} catch (IOException ioe) {
			LoggingUtil.error("IOException called by CarIdSerializeDAO loadCarID();");

		} catch (ClassNotFoundException c) {
			LoggingUtil.error("ClassNotFoundException called by CarIdSerializeDAO loadCarID();");

		}

		return carIdList;
	}

}
