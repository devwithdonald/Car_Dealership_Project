package com.donald.users;

import java.util.ArrayList;
import java.util.List;

public class MasterOfferList {

	// only one master offerList
	private static final List<Offer> offerList = new ArrayList<>();

	public static List<Offer> getOfferlist() {
		return offerList;
	}

}
