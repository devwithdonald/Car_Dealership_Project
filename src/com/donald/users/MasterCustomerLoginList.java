package com.donald.users;

import java.util.HashMap;
import java.util.Map;

public class MasterCustomerLoginList {

	private static final Map<String,String> CustomerLoginMap = new HashMap<>();

	public static Map<String,String> getCustomerloginmap() {
		return CustomerLoginMap;
	}
	
	
}
