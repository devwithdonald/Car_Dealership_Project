package com.donald.tests;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.donald.screens.CustomerScreen;
import com.donald.users.Customer;
import com.donald.users.CustomerBase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerScreenTests {

	private static CustomerScreen cs;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cs = new CustomerScreen();
		
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
	public void AtestFailedLoginVerification() {
		System.out.println("failed login");
		boolean result = cs.loginVerification();
		
		assertEquals(false, result);
	}
	
	@Test
	public void BtestPassedLoginVerification() {
		System.out.println("passed login");
		boolean result = cs.loginVerification();
		
		assertEquals(true, result);
	}
	
	@Test
	public void CtestEscapeFromMenu() {
		System.out.println("escape menu");
		boolean result = cs.display();
		
		assertEquals(false, result);
	}
	
	@Test
	public void DtestExitCustomerAccessMenu() {
		System.out.println("leave option");
		boolean result = cs.customerAccessMenu();
		
		assertEquals(false, result);
	}
	

	
	


}
