package com.donald.dao;

import java.util.Map;

import com.donald.users.MasterCustomerLoginList;

public interface LoginListDAO {

	public void saveLoginList(Map<String,String> CustomerLoginMap);
	
	public Map<String,String> loadFile();
	
}
