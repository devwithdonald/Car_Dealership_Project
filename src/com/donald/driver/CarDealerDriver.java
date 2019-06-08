package com.donald.driver;

import com.donald.dao.CarIdSerializeDAO;
import com.donald.dao.CarLotSerializeDAO;
import com.donald.dao.CustomerListSerializeDAO;
import com.donald.dao.LoginListSerializeDAO;
import com.donald.dao.OfferSerializeDAO;
import com.donald.screens.CustomerScreen;
import com.donald.screens.EmployeeScreen;
import com.donald.services.WebServiceImpl;
import com.donald.users.CarId;
import com.donald.users.CarIdCounter;
import com.donald.users.CarLot;
import com.donald.users.CustomerBase;
import com.donald.users.MasterCustomerLoginList;
import com.donald.users.MasterOfferList;

public class CarDealerDriver {

	public static void main(String[] args) {


		//persistence
		CarIdSerializeDAO carIdList = new CarIdSerializeDAO();
		CarLotSerializeDAO carList = new CarLotSerializeDAO();
		CustomerListSerializeDAO customerList = new CustomerListSerializeDAO();
		LoginListSerializeDAO loginList = new LoginListSerializeDAO();
		OfferSerializeDAO offerList = new OfferSerializeDAO();
		
		
		if(carIdList.loadCarId() != null) {
			CarIdCounter.getCaridcounter().clear();
			CarIdCounter.getCaridcounter().addAll(carIdList.loadCarId());
		}
		if(carList.loadCarLot() != null) {
			CarLot.getCarlot().clear();
			CarLot.getCarlot().addAll(carList.loadCarLot());
		}
		if(customerList.loadCustomerList() != null) {
			CustomerBase.getCustomerlist().clear();
			CustomerBase.getCustomerlist().addAll(customerList.loadCustomerList());
		}
		if(loginList.loadLoginList() != null) {
			MasterCustomerLoginList.getCustomerloginmap().clear();
			MasterCustomerLoginList.getCustomerloginmap().putAll(loginList.loadLoginList());
		}
		if(offerList.loadOfferList() != null) {
			MasterOfferList.getOfferlist().clear();
			MasterOfferList.getOfferlist().addAll(offerList.loadOfferList());
		}
		
		
		WebServiceImpl web = new WebServiceImpl();
		// initial screen
		// whether it returns make a customer screen or a employee screen (handles all
		// of employee stuff)

		boolean keepGoing = true;

		do {
			

			
			String screen = web.initialScreen();
			//load here????????
			

			if (screen.equals("employee")) {
				EmployeeScreen es = new EmployeeScreen();

				// if employee didn't pass verification go to top of screen
				if (!es.display()) {
					//save
					carIdList.saveCarId(CarIdCounter.getCaridcounter());
					carList.saveCarLot(CarLot.getCarlot());
					customerList.saveCustomerList(CustomerBase.getCustomerlist());
					loginList.saveLoginList(MasterCustomerLoginList.getCustomerloginmap());
					offerList.saveOfferList(MasterOfferList.getOfferlist());
					// go to top of loop
					continue;
				}

			} else if (screen.equals("customer")) {
				CustomerScreen cs = new CustomerScreen();

				// if customer didn't pass verification go to top of screen
				if (!cs.display()) {
					//save
					carIdList.saveCarId(CarIdCounter.getCaridcounter());
					carList.saveCarLot(CarLot.getCarlot());
					customerList.saveCustomerList(CustomerBase.getCustomerlist());
					loginList.saveLoginList(MasterCustomerLoginList.getCustomerloginmap());
					offerList.saveOfferList(MasterOfferList.getOfferlist());
					// go to top of loop
					continue;
				}
			}


		} while (keepGoing);
		


	}

}
