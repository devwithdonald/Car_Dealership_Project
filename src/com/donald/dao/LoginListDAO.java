package com.donald.dao;

import com.donald.users.MasterCustomerLoginList;

public interface LoginListDAO {

	public void saveLoginList(MasterCustomerLoginList masterCustomerLoginList);
	
	public MasterCustomerLoginList loadFile();
	
}
