package com.donald.users;

public class Employee {

	//consistent for the employees
	private final String USERNAME = "admin";
	private final String PASSWORD = "admin";
	
	public String getUSERNAME() {
		return USERNAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	
	@Override
	public String toString() {
		return "Employee [USERNAME=" + USERNAME + ", PASSWORD=" + PASSWORD + "]";
	}
	
	 
	
}
