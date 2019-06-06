package com.donald.users;

public class Offer {


	//these offerIDs need to unique
	public static int offerCounterID = 1;
	private int offerID;
	
	private Car offerCar;
	private Customer offerer;
	private Integer offerPrice;
	
	
	

	
	public Offer(Car offerCar, Customer offerer, Integer offerPrice) {
		super();
		this.offerCar = offerCar;
		this.offerer = offerer;
		this.offerPrice = offerPrice;
		this.offerID = offerCounterID;
		offerCounterID++;
	}

	public int getOfferID() {
		return offerID;
	}

	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}

	public Car getOfferCar() {
		return offerCar;
	}
	
	public void setOfferCar(Car offerCar) {
		this.offerCar = offerCar;
	}
	public Customer getOfferer() {
		return offerer;
	}
	public void setOfferer(Customer offerer) {
		this.offerer = offerer;
	}
	public Integer getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(Integer offerPrice) {
		this.offerPrice = offerPrice;
	}

	@Override
	public String toString() {
		return "Offer [offerID=" + offerID + ", offerCarType=" + offerCar.getCarType() + ", offerCarOriginalPrice=" + offerCar.getPrice() +  ", offerer=" + offerer.getUsername() + ", offerPrice="
				+ offerPrice + "]";
	}


	


}
