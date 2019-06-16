package com.donald.tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.donald.services.CustomerServiceImpl;
import com.donald.users.UniqueOrderIDCounter;

public class CustomerServiceTests {
	
	private static CustomerServiceImpl csi;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		csi = new CustomerServiceImpl();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		UniqueOrderIDCounter.getUniqeidcounter().clear();
	}

//	@Test
//	public void addingOneOffer() {
//		//before 
//		Integer initial = UniqueOrderIDCounter.getUniqeidcounter().size();
//		//check initial + 1 
//		Integer result = initial + 1;
//	
//		//adding to the UniqueOrder list by 1
//		Integer getUniqueOfferID = csi.getUniqueOfferId(1);
//		
//		assertEquals(result, getUniqueOfferID);
//	}
//	
//	@Test
//	public void addingTwoOffers() {
//		//before 
//		Integer initial = UniqueOrderIDCounter.getUniqeidcounter().size();
//		//check initial + 1 
//		Integer result = initial + 2;
//	
//		//adding to the UniqueOrder list by 2
//		Integer getUniqueOfferID = csi.getUniqueOfferId(1);
//		getUniqueOfferID = csi.getUniqueOfferId(1);
//		
//		assertEquals(result, getUniqueOfferID);
//	}
//	
//	@Test
//	public void offerArgumentDoesntMatter() {
//		//before 
//		Integer initial = UniqueOrderIDCounter.getUniqeidcounter().size();
//		//check initial + 1 
//		Integer result = initial + 2;
//	
//		//adding to the UniqueOrder list by 2
//		Integer getUniqueOfferID = csi.getUniqueOfferId(458998);
//		getUniqueOfferID = csi.getUniqueOfferId(94958301);
//		
//		assertEquals(result, getUniqueOfferID);
//	}

}
