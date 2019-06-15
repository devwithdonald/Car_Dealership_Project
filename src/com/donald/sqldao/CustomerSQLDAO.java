package com.donald.sqldao;

import java.util.List;

import com.donald.users.Customer;

public interface CustomerSQLDAO {

	public void insertCustomer(Customer cust);
	
	public void updateCustomerOnAcceptedOffer(Customer cust);
	
	public List<Customer> getAllCustomers();
	
	public Customer getCustomerById(int id);
	
}
