package com.donald.dao;

import java.util.List;
import java.util.Map;

import com.donald.users.Customer;

public interface CustomerListDAO {

	public void saveCustomerList(List<Customer> customerList);
	
	public List<Customer> loadCustomerList();
}
