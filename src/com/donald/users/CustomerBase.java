package com.donald.users;

import java.util.ArrayList;
import java.util.List;

public class CustomerBase {

	private static final List<Customer> customerList = new ArrayList<>();

	public static List<Customer> getCustomerlist() {
		return customerList;
	}

}
