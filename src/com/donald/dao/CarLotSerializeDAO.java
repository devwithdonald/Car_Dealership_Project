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


public class CarLotSerializeDAO implements CarLotDAO{

	@Override
	public void saveCarLot(List<Car> carLot) {
		
		try {
			FileOutputStream fos = new FileOutputStream("CarLotData");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(carLot);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}

	@Override
	public List<Car> loadCarLot() {
		List<Car> carLot = null;
		
		try {
			FileInputStream fis = new FileInputStream("CarLotData");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			//loads list
			carLot = (ArrayList) ois.readObject();
			
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
		}
		
//		for(Car car : carLot) {
//			System.out.println(car);
//		}
		
		return carLot;
	}
	
	

}
