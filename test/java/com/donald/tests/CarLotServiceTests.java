package com.donald.tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.donald.services.CarLotServiceImpl;
import com.donald.services.CustomerServiceImpl;
import com.donald.users.Car;
import com.donald.users.CarLot;
import com.donald.users.Customer;
import com.donald.users.MasterOfferList;
import com.donald.users.Offer;

public class CarLotServiceTests {

	private static CarLotServiceImpl clsi;
	private static Car car;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		clsi = new CarLotServiceImpl();

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//adding cars
		CarLot.getCarlot().add(new Car("dealer", "600", "ferrari", true, 1));
		CarLot.getCarlot().add(new Car("dealer", "400", "butter", true, 2));
		CarLot.getCarlot().add(new Car("dealer", "90", "mustang", true, 3));
		CarLot.getCarlot().add(new Car("dealer", "150", "chevy", true, 4));

		// adding offers,
		MasterOfferList.getOfferlist()
				.add(new Offer(new Car("dealer", "400", "car1", true, 1), new Customer("user1", "pass1"), 100, 1));
		MasterOfferList.getOfferlist()
				.add(new Offer(new Car("dealer", "500", "car2", true, 2), new Customer("user2", "pass2"), 200, 2));
		MasterOfferList.getOfferlist()
				.add(new Offer(new Car("dealer", "600", "car3", true, 3), new Customer("user3", "pass3"), 300, 3));

	}

	@After
	public void tearDown() throws Exception {
		CarLot.getCarlot().clear();
		MasterOfferList.getOfferlist().clear();
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
		assertEquals(2, CarLot.getCarlot().size());
	}

	@Test
	public void testInvalidRemovalEmpty() {
		clsi.removeCar(98308);
		assertEquals(4, CarLot.getCarlot().size());
	}

	@Test
	public void addOneCarTest() {
		int before = CarLot.getCarlot().size();
		// size should go up 1
		clsi.addCar();
		int after = before + 1;
		assertEquals(after, CarLot.getCarlot().size());

	}

	@Test
	public void addTwoCarTest() {
		int before = CarLot.getCarlot().size();
		// size should go up 1
		clsi.addCar();
		clsi.addCar();
		int after = before + 2;
		assertEquals(after, CarLot.getCarlot().size());

	}



}
