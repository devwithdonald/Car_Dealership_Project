package com.donald.user;

public interface EmployeeAction {
	
	//add a car to the car lot list
	public void addCar(Object car);
	
	//remove car from the car lot list
	public void removeCar(Object car);
	
	//approve the offer of the car
	public void approveOffer(Object car, int offerPrice);
	
	//view payments for the car 
	public void viewAllCarPayments();
	
	
	

	
	

}
