package com.donald.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.donald.sqldao.CarPostgresDAOImpl;
import com.donald.users.Car;

public class CarDAOTests {
	
	private static CarPostgresDAOImpl carDAO;
	private static Car testCar;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		carDAO = new CarPostgresDAOImpl();
		testCar = new Car("dealer", "9999", "test", true);
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
//	public void testInsertCar() {
//		int result = carDAO.insertCar(testCar);
//		assertEquals(22, result);
//	}
	
	@Test
	public void testGetCarList() {
		List<Car> result = carDAO.getAllCars();
		assertEquals(9, result.size());
	}
	
	@Test
	public void testGetCarsByCustomerId() {
		List<Car> result = carDAO.getCarsByCustomerId(1);
		assertEquals(2, result.size());
	}
	
	@Test
	public void testGetCarByCarId() {
		Car result = carDAO.getCarById(3);
		assertEquals(3, result.getCarID());
	}
	
	@Test
	public void testGetCarByCarIdNOID() {
		Car result = carDAO.getCarById(11245);
		
		assertEquals(null, result);
		
	}

}
