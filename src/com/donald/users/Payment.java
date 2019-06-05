package com.donald.users;

public class Payment {

	private int amountPaid;
	private Car car; //on what car the payment is made
	
	//get payment left for a car! separate method!
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Payment(int amountPaid, Car car) {
		super();
		this.amountPaid = amountPaid;
		this.car = car;
	}
	

	
	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amountPaid;
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (amountPaid != other.amountPaid)
			return false;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Payment [amountPaid=" + amountPaid + ", car=" + car + "]";
	}
	
	
	
	
	
	
	
	
	
}
