package com.donald.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import com.donald.users.MasterCustomerLoginList;

public class LoginListSerializeDAO implements LoginListDAO {

	
	
	//serialize the list instead of the individual objects, then have a list of lists 
	
	
	//SAVE OBJECT LIST?
	
	@Override
	public void saveLoginList(Map<String,String> CustomerLoginMap) {

		
		//wrong!!
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
	public Map<String,String> loadFile() {
		// TODO Auto-generated method stub
		return null;
	}

}
