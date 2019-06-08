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

public class CarIdSerializeDAO implements CarIdDAO{

	@Override
	public void saveCarId(List<CarId> carId) {
		
		try {
			FileOutputStream fos = new FileOutputStream("CarIdData");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(carId);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
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
			e.printStackTrace();
			return null;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
		}

//		for (Customer customer : customerList) {
//			System.out.println("username" + customer.getUsername() + "password" + customer.getPassword() + "ID"
//					+ customer.getCustomerID());
//		}

		return carIdList;
	}

}
