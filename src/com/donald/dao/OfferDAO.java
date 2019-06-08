package com.donald.dao;

import java.util.List;

import com.donald.users.Offer;

public interface OfferDAO {
	
	public void saveOfferList(List<Offer> offerList);
	
	public List<Offer> loadOfferList();
}
