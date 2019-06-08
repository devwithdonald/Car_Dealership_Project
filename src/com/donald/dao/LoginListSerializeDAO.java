package com.donald.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class LoginListSerializeDAO implements LoginListDAO {

	
	
	//serialize the list instead of the individual objects, then have a list of lists 
	
	
	//SAVE OBJECT LIST?
	
	@Override
	public void saveLoginList(Map<String,String> CustomerLoginMap) {

		
		try {
			FileOutputStream fos = new FileOutputStream("LoginListData");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(CustomerLoginMap);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public Map<String,String> loadLoginList() {
		
		Map<String,String> CustomerLoginMap = null;
		
		try {
			FileInputStream fis = new FileInputStream("LoginListData");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			//loads list
			CustomerLoginMap = (HashMap) ois.readObject();
			
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
		
		for(Map.Entry<String, String> entry: CustomerLoginMap.entrySet()) {
			System.out.println("Key= " + entry.getKey() + " Value= "+ entry.getValue());
		}
		
		return CustomerLoginMap;
	}

}
