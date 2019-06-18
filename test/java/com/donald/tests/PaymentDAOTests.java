package com.donald.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.donald.sqldao.PaymentPostgresDAOImpl;
import com.donald.users.Payment;

public class PaymentDAOTests {
	
	private static PaymentPostgresDAOImpl paymentDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		paymentDAO = new PaymentPostgresDAOImpl();
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
	public void testGetAllPayments() {
		List<Payment> result = paymentDAO.getAllPayments();
		assertEquals(3, result.size());
	}
	
	@Test
	public void testGetPaymentsByCustomerId() {
		List<Payment> result = paymentDAO.getAllPayments();
		assertEquals(3, result.size());
	}
	
	

}
