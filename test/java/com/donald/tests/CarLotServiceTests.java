package com.donald.tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.donald.services.CarLotServiceImpl;
import com.donald.users.Car;
import com.donald.users.CarLot;

public class CarLotServiceTests {
	
	private static CarLotServiceImpl clsi;
	private static Car car;
	private static CarLot carLot;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		clsi = new CarLotServiceImpl();


	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		CarLot.getCarlot().add(new Car("dealer", "600", "ferrari", true, 1));
		CarLot.getCarlot().add(new Car("dealer", "400", "butter", true, 2));
		CarLot.getCarlot().add(new Car("dealer", "90", "mustang", true, 3));
		CarLot.getCarlot().add(new Car("dealer", "150", "chevy", true, 4));
	}

	@After
	public void tearDown() throws Exception {
		CarLot.getCarlot().clear();
	}

	
	@Test
	public void testZeroInput() {
		assertEquals(car, clsi.matchCarId(0));
	}
	
	@Test
	public void testHighInput() {
		assertEquals(car, clsi.matchCarId(38483947));
	}
	
	
	@Test
	public void testOneCarRemoval() {
		clsi.removeCar(1);
		assertEquals(3, CarLot.getCarlot().size());
	}
	
	
	@Test
	public void testMultCarRemoval() {
		clsi.removeCar(2);
		clsi.removeCar(3);
		assertEquals(2,CarLot.getCarlot().size());
	}
	
	@Test
	public void testInvalidRemovalEmpty() {
		clsi.removeCar(98308);
		assertEquals(4, CarLot.getCarlot().size());
	}
	
	
	

	



}
