package com.donald.users;

import java.util.ArrayList;
import java.util.List;

public class Car {

	public static int carIDCounter = 1; 
	private int carID;
	
	private String ownerUsername;
	private String price; //balance
	private String carType;
	private Boolean forSale;
	private List<Offer> carOfferList;
	
	
	public Car() {
		super();
		// TODO Auto-generated constructor stub
		this.carID = Car.carIDCounter;
		Car.carIDCounter++;
	}

	// TODO NEED LOGIC
	public Car(String ownerUsername, String price, String carType, Boolean forSale) {
		super();
		this.ownerUsername = ownerUsername;
		this.price = price;
		this.carType = carType;
		this.forSale = forSale;
		this.carID = Car.carIDCounter;
		this.carOfferList = new ArrayList<>();
		Car.carIDCounter++;
	}

	public List<Offer> getCarOfferList() {
		return carOfferList;
	}

	public void setCarOfferList(List<Offer> carOfferList) {
		this.carOfferList = carOfferList;
	}

	public String getOwnerUsername() {
		return ownerUsername;
	}

	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Boolean getForSale() {
		return forSale;
	}

	public void setForSale(Boolean forSale) {
		this.forSale = forSale;
	}
	
	public int getCarID() {
		return carID;
	}




	@Override
	public String toString() {
		return "Car [carID=" + carID + ", ownerUsername=" + ownerUsername + ", price=" + price + ", carType=" + carType
				+ ", forSale=" + forSale + ", carOfferList=" + carOfferList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carType == null) ? 0 : carType.hashCode());
		result = prime * result + ((forSale == null) ? 0 : forSale.hashCode());
		result = prime * result + ((ownerUsername == null) ? 0 : ownerUsername.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		Car other = (Car) obj;
		if (carType == null) {
			if (other.carType != null)
				return false;
		} else if (!carType.equals(other.carType))
			return false;
		if (forSale == null) {
			if (other.forSale != null)
				return false;
		} else if (!forSale.equals(other.forSale))
			return false;
		if (ownerUsername == null) {
			if (other.ownerUsername != null)
				return false;
		} else if (!ownerUsername.equals(other.ownerUsername))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

}
