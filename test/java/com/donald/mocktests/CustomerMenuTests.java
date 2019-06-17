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

import com.donald.screens.CustomerScreen;
import com.donald.services.CarLotServiceImpl;
import com.donald.services.CustomerServiceImpl;
import com.donald.users.Customer;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerMenuTests {
	
	@Mock
	private CarLotServiceImpl clsi = new CarLotServiceImpl();
	
	@Mock
	private CustomerServiceImpl csi = new CustomerServiceImpl();
	
	@InjectMocks
	private CustomerScreen cs = new CustomerScreen();
	
	@Before
	public void setUp() throws Exception {
		cs.display();
	}
	
	@Test
	public void AtestCustomerMenuViewCarLot() {
		verify(clsi, times(1)).viewCarLot();
	}
	

	


}
