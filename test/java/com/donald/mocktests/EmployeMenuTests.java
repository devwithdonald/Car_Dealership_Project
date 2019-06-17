package com.donald.mocktests;

import static org.junit.Assert.assertEquals;
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
public class EmployeMenuTests {

	@Mock
	private CarLotServiceImpl cls;

	@Mock
	private EmployeeServiceImpl esi;
	
	@Mock
	private WebServiceImpl wsi;
	
	@InjectMocks
	private EmployeeScreen es = new EmployeeScreen();


	
	@Before
	public void setUp() throws Exception {
		es.display();
	}

	@Test
	public void AtestMenuAddCar() {

		verify(cls, times(1)).addCar();
	}

	@Test
	public void BtestMenuOfferDecisionMenu() {

		verify(esi, times(1)).offerDecisionMenu();
	}
	
	@Test
	public void CtestMenuRemoveCarMenu() {

		verify(cls, times(1)).removeCarMenu();
	}
	
	@Test
	public void DtestMenuViewAllPayments() {

		verify(wsi, times(1)).viewAllPayments();
	}
	
	@Test
	public void EtestFailedLoginVerification() {
		
		assertEquals(false, es.loginVerification());
	}
	
	@Test
	public void FtestPassedLoginVerification() {
		
		assertEquals(true, es.loginVerification());
	}

}
