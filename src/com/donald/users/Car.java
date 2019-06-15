package com.donald.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Car implements Serializable{

	private Integer carID; // good!
	private String ownerUsername; // good !
	private String price; // good!
	private String carType; // good! 
	private Boolean forSale; // good!
	private List<Offer> carOfferList; // need statement to populate, strictly for java use
	private String purchasedPrice; // good! 
	
	public Car() {
		
	}

	public Car(String ownerUsername, String price, String carType, Boolean forSale, Integer carID) {
		super();
		this.ownerUsername = ownerUsername;
		this.price = price;
		this.carType = carType;
		this.forSale = forSale;
		this.carID = carID;
		this.carOfferList = new ArrayList<>();
		this.purchasedPrice = price;

	}

	public String getPurchasedPrice() {
		return purchasedPrice;
	}

	public void setPurchasedPrice(String purchasedPrice) {
		this.purchasedPrice = purchasedPrice;
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
		return "Car [boatID=" + carID + ", ownerUsername=" + ownerUsername + ", dealerPrice=" + price + ", purchasedPrice=" + purchasedPrice + ", boatType=" + carType
				+ ", forSale=" + forSale + ", boatOfferList=" + carOfferList + "]";
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
