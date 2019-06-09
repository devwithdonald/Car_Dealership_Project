package com.donald.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.donald.util.LoggingUtil;

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
			LoggingUtil.warn("IOException called by LoginListSerializeDAO saveLoginList();");
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
			LoggingUtil.warn("FileNotFoundException called by LoginListSerializeDAO loadLoginList();");
			return null;
		} catch (IOException ioe) {
			LoggingUtil.warn("IOException called by LoginListSerializeDAO loadLoginList();");
		} catch (ClassNotFoundException c) {
			LoggingUtil.warn("ClassNotFoundException called by LoginListSerializeDAO loadLoginList();");
		}

		return CustomerLoginMap;
	}

}
