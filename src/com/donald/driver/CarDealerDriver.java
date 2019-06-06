package com.donald.driver;

import com.donald.screens.CustomerScreen;
import com.donald.screens.EmployeeScreen;
import com.donald.services.WebServiceImpl;

public class CarDealerDriver {

	public static void main(String[] args) {
		WebServiceImpl web = new WebServiceImpl();

		// initial screen
		// whether it returns make a customer screen or a employee screen (handles all
		// of employee stuff)

		boolean keepGoing = true;

		do {
			String screen = web.initialScreen();
			
			if (screen.equals("employee")) {
				EmployeeScreen es = new EmployeeScreen();
				
				//if employee didn't pass verification go to top of screen
				if(!es.employeeOption()) {
					//go to top of loop
					continue;
				}
					
			} else if (screen.equals("customer")) {
				CustomerScreen cs = new CustomerScreen();
				
				//if customer didn't pass verification go to top of screen
				if(!cs.customerOption()) {
					//go to top of loop
					continue;
				}
			}

		} while (keepGoing);

	}

}
