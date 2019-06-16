package com.donald.users;

public class Employee {

	// One employee login for everyone
	private static final Employee employee = new Employee();

	public static Employee getEmployee() {
		return employee;
	}

	// consistent for the employees
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
