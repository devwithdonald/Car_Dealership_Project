package com.donald.users;

import java.io.Serializable;

public class Offer implements Serializable {

	private int offerID;
	private Car offerCar;
	private Customer offerer;
	private Integer offerPrice;

	public Offer(Car offerCar, Customer offerer, Integer offerPrice, Integer offerID) {
		super();
		this.offerCar = offerCar;
		this.offerer = offerer;
		this.offerPrice = offerPrice;
		this.offerID = offerID;
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
		return "Offer [offerID=" + offerID + ", offerBoatID=" + offerCar.getCarID() + ", offerBoatType="
				+ offerCar.getCarType() + ", offerBoatOriginalPrice=" + offerCar.getPrice() + ", offerer="
				+ offerer.getUsername() + ", offerPrice=" + offerPrice + "]";
	}

}
