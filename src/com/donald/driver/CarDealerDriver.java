package com.donald.driver;

import com.donald.screens.CustomerScreen;
import com.donald.screens.EmployeeScreen;
import com.donald.services.WebServiceImpl;
import com.donald.util.LoggingUtil;

public class CarDealerDriver {

	public static void main(String[] args) {

		WebServiceImpl web = new WebServiceImpl();

		boolean keepGoing = true;

		do {
			LoggingUtil.trace("Do Loop in Main - Start");

			String screen = web.initialScreen();

			if (screen.equals("employee")) {
				LoggingUtil.trace("Do Loop in Main - if -> 'employee'");
				EmployeeScreen es = new EmployeeScreen();

				if (!es.display()) {
					LoggingUtil.trace("Do Loop in Main - saving files - employee");

					continue;
				}

			} else if (screen.equals("customer")) {
				LoggingUtil.trace("Do Loop in Main - if -> 'customer'");
				CustomerScreen cs = new CustomerScreen();

				// if customer didn't pass verification go to top of screen
				if (!cs.display()) {
					LoggingUtil.trace("Do Loop in Main - saving files - employee");

					continue;
				}
			}

		} while (keepGoing);

	}

}
