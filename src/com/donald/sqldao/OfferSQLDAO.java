package com.donald.sqldao;

import java.util.List;

import com.donald.users.Customer;
import com.donald.users.Offer;

public interface OfferSQLDAO {
	
	public void insertOffer(Offer offer);
	
	public int makeOffer(Customer customer, int carId, int offerPrice);
	
	public int updateOffer(Offer offer, int statusId);
	
	public int updateOfferOnAcceptance(int offerId, Customer buyer);
	
	public List<Offer> getAllOffers();
	
	public List<Offer> getOffersByCustomerId(int id);
	
	public List<Offer> getOffersByCarId(int id);
	
	public Offer getOfferById(int id);
	
	public int updateOfferOnCarRemoval(int carId);
	
	public int updateOfferOnRejection(int offerId);
}
