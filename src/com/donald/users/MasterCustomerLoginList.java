package com.donald.users;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MasterCustomerLoginList implements Serializable{

	private static final Map<String,String> CustomerLoginMap = new HashMap<>();

	public static Map<String,String> getCustomerloginmap() {
		return CustomerLoginMap;
	}
	
	
}
