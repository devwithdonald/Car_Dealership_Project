package com.donald.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.donald.sqldao.CustomerPostGresDAOImpl;
import com.donald.sqldao.OfferPostgresDAOImpl;
import com.donald.users.Offer;

public class OfferDAOTests {

	private static OfferPostgresDAOImpl offerDAO;
	private static CustomerPostGresDAOImpl customerDAO;
	private static Offer offer;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		offerDAO = new OfferPostgresDAOImpl();
		offer = offerDAO.getOfferById(16);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUpdateOffer() {
		
		assertEquals(1, offerDAO.updateOffer(offer,2));
	}
	
	@Test
	public void testUpdateOfferOnCarRemoval() {
		assertEquals(1, offerDAO.updateOfferOnCarRemoval(2));
	}
	
	@Test
	public void testUpdateOfferOnRejection() {
		assertEquals(1, offerDAO.updateOfferOnRejection(3));
	}
	
	@Test
	public void testGetAllOffers() {
		List<Offer> result = offerDAO.getAllOffers();
		assertEquals(1,result.size());
	}
	
	@Test
	public void testGetOffersByCustomerId() {
		List<Offer> result = offerDAO.getOffersByCustomerId(4);
		assertEquals(3,result.size());
	}
	
	@Test
	public void testGetOffersByCarId() {
		List<Offer> result = offerDAO.getOffersByCarId(12);
		assertEquals(1,result.size());
	}
}
