package com.donald.users;

import java.util.List;

public class Customer {
	
	private String username;
	private String password;
	private List<Car> carsOwned;
	private int balance;
	//
	private List<Offer> pendingOffers;
	
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
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
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

	
	

}
