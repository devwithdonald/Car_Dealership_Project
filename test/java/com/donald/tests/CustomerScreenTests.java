package com.donald.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.donald.screens.CustomerScreen;
import com.donald.users.Customer;
import com.donald.users.CustomerBase;

public class CustomerScreenTests {

	private static CustomerScreen cs;

	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cs = new CustomerScreen();
		
		// CustomerBase list
		CustomerBase.getCustomerlist().add(new Customer("userone", "passone"));
		CustomerBase.getCustomerlist().add(new Customer("usertwo", "passtwo"));
		CustomerBase.getCustomerlist().add(new Customer("userthree", "passthree"));
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

	//always returns a customer
	@Test
	public void getCustomerFromList() {
		Customer c = cs.customerLoginMatch("userone");
		
		assertEquals(c, cs.customerLoginMatch("userone"));
	}
	
	//always returns a customer
	@Test
	public void customerNotInList() {
		Customer c = cs.customerLoginMatch("notinhere");
		
		assertEquals(c, cs.customerLoginMatch("notinhere"));
	}
	


}
