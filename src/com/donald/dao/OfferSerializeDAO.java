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
import com.donald.users.Offer;

public class OfferSerializeDAO implements OfferDAO {

	@Override
	public void saveOfferList(List<Offer> offerList) {
		
		try {
			FileOutputStream fos = new FileOutputStream("OfferListData");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(offerList);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	@Override
	public List<Offer> loadOfferList() {
		
		List<Offer> offerList = null;
		
		try {
			FileInputStream fis = new FileInputStream("OfferListData");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			//loads list
			offerList = (ArrayList) ois.readObject();
			
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
		
		for(Offer offer : offerList) {
			System.out.println(offer);
		}
		
		return offerList;
	}

}
