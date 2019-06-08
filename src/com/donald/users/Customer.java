package com.donald.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {

	private static int customerIDCounter = 1;
	private int customerID;

	private List<Payment> localPaymentList;
	private boolean makingPayments = false;
	private String username;
	private String password;
	private List<Car> carsOwned;
	private Integer balance;
	private List<Offer> pendingOffers;
	private Double monthlyPayment;

	
	

	public Customer(String username, String password) {
		super();
		this.localPaymentList = new ArrayList<>();
		this.makingPayments = false;
		this.username = username;
		this.password = password;
		this.carsOwned = new ArrayList<>();
		this.balance = 0;
		this.pendingOffers = new ArrayList<>();
		this.monthlyPayment = 0.0;
		this.customerID = customerIDCounter;
		customerIDCounter++;
	}

	public Double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(Double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public int getCustomerID() {
		return customerID;
	}

	public boolean isMakingPayments() {
		return makingPayments;
	}

	public void setMakingPayments(boolean makingPayments) {
		this.makingPayments = makingPayments;
	}

	public List<Payment> getLocalPaymentList() {
		return localPaymentList;
	}

	public void setLocalPaymentList(List<Payment> localPaymentList) {
		this.localPaymentList = localPaymentList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Car> getCarsOwned() {
		return carsOwned;
	}

	public void setCarsOwned(List<Car> carsOwned) {
		this.carsOwned = carsOwned;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public List<Offer> getPendingOffers() {
		return pendingOffers;
	}

	public void setPendingOffers(List<Offer> pendingOffers) {
		this.pendingOffers = pendingOffers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + balance;
		result = prime * result + ((carsOwned == null) ? 0 : carsOwned.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Customer other = (Customer) obj;
		if (balance != other.balance)
			return false;
		if (carsOwned == null) {
			if (other.carsOwned != null)
				return false;
		} else if (!carsOwned.equals(other.carsOwned))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

//	@Override
//	public String toString() {
//		return "Customer [customerID=" + customerID + ", username=" + username + ", password=" + password + "]";
//	}

}
