package com.donald.mocktests;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.donald.screens.EmployeeScreen;
import com.donald.services.CarLotServiceImpl;
import com.donald.services.EmployeeServiceImpl;
import com.donald.services.WebServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarLotServiceTests {
	


	@InjectMocks
	private CarLotServiceImpl clsi = new CarLotServiceImpl();


	
	@Before
	public void setUp() throws Exception {

	}



}
