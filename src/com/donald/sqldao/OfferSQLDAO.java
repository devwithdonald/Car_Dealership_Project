package com.donald.sqldao;

import java.util.List;

import com.donald.users.Customer;
import com.donald.users.Offer;

public interface OfferSQLDAO {
	
	public void insertOffer(Offer offer);
	
	public void makeOffer(Customer customer, int carId, int offerPrice);
	
	public void updateOffer(Offer offer, int statusId);
	
	public void updateOfferOnAcceptance(int offerId, Customer buyer);
	
	public List<Offer> getAllOffers();
	
	public List<Offer> getOffersByCustomerId(int id);
	
	public List<Offer> getOffersByCarId(int id);
	
	public Offer getOfferById(int id);
	
	public void updateOfferOnCarRemoval(int carId);
	
	public void updateOfferOnRejection(int offerId);
}
