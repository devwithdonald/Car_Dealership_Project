package com.donald.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.donald.sqldao.CustomerPostGresDAOImpl;
import com.donald.users.Car;
import com.donald.users.Customer;

public class CustomerDAOTests {
	
	private static CustomerPostGresDAOImpl customerDAO;
	private static Customer cust;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		customerDAO = new CustomerPostGresDAOImpl();
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

//	@Test
//	public void testRegisterCustomer() {
//		customerDAO.registerCustomer("tester1", "tester1");
//		
//		assertEquals(10,customerDAO.getAllCustomers().size());
//	}
	
	@Test
	public void testGetAllCustomers() {
		List<Customer> result = customerDAO.getAllCustomers();
		assertEquals(10, result.size());
	}
	
	@Test
	public void testGetCustomerById() {
		Customer result = customerDAO.getCustomerById(10);
		assertEquals(10, result.getCustomerID());
	}
	
	@Test
	public void testGetCustomerByIdNOID() {
		Customer result = customerDAO.getCustomerById(11245);
		
		assertEquals(null, result);	
	}
	
	@Test 
	public void testGetCustomerByUsername() {
		Customer result = customerDAO.getCustomerByUsername("tester1");
		assertEquals("tester1", result.getUsername());
	}
	
	

}
