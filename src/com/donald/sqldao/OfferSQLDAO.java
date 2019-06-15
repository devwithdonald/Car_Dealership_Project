package com.donald.sqldao;

import java.util.List;

import com.donald.users.Offer;

public interface OfferSQLDAO {
	
	public void insertOffer(Offer offer);
	
	public void updateOffer(Offer offer, int offerInt);
	
	public List<Offer> getAllOffers();
	
	public List<Offer> getOffersByCustomerId(int id);
	
	public List<Offer> getOffersByCarId(int id);
	
	public Offer getOfferById(int id);
}
