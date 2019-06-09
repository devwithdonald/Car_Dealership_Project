package com.donald.driver;

import com.donald.dao.CarIdSerializeDAO;
import com.donald.dao.CarLotSerializeDAO;
import com.donald.dao.CustomerListSerializeDAO;
import com.donald.dao.LoginListSerializeDAO;
import com.donald.dao.OfferSerializeDAO;
import com.donald.dao.UniqueOrderIDSerializeDAO;
import com.donald.dao.UniqueOrderIdDAO;
import com.donald.screens.CustomerScreen;
import com.donald.screens.EmployeeScreen;
import com.donald.services.WebServiceImpl;
import com.donald.users.CarId;
import com.donald.users.CarIdCounter;
import com.donald.users.CarLot;
import com.donald.users.CustomerBase;
import com.donald.users.MasterCustomerLoginList;
import com.donald.users.MasterOfferList;
import com.donald.users.UniqueOrderIDCounter;
import com.donald.util.LoggingUtil;

public class CarDealerDriver {

	public static void main(String[] args) {


		//persistence
		CarIdSerializeDAO carIdList = new CarIdSerializeDAO();
		CarLotSerializeDAO carList = new CarLotSerializeDAO();
		CustomerListSerializeDAO customerList = new CustomerListSerializeDAO();
		LoginListSerializeDAO loginList = new LoginListSerializeDAO();
		OfferSerializeDAO offerList = new OfferSerializeDAO();
		UniqueOrderIDSerializeDAO orderIdList = new UniqueOrderIDSerializeDAO();
		
		if(carIdList.loadCarId() != null) {
			LoggingUtil.trace("LoginListSerializeDAO load");
			CarIdCounter.getCaridcounter().clear();
			CarIdCounter.getCaridcounter().addAll(carIdList.loadCarId());
		}
		if(carList.loadCarLot() != null) {
			LoggingUtil.trace("CarLotSerializeDAO load");
			CarLot.getCarlot().clear();
			CarLot.getCarlot().addAll(carList.loadCarLot());
		}
		if(customerList.loadCustomerList() != null) {
			LoggingUtil.trace("CustomerListSerializeDAO load");
			CustomerBase.getCustomerlist().clear();
			CustomerBase.getCustomerlist().addAll(customerList.loadCustomerList());
		}
		if(loginList.loadLoginList() != null) {
			LoggingUtil.trace("LoginListSerializeDAO load");
			MasterCustomerLoginList.getCustomerloginmap().clear();
			MasterCustomerLoginList.getCustomerloginmap().putAll(loginList.loadLoginList());
		}
		if(offerList.loadOfferList() != null) {
			LoggingUtil.trace("OFferSerializeDAO load");
			MasterOfferList.getOfferlist().clear();
			MasterOfferList.getOfferlist().addAll(offerList.loadOfferList());
		}
		if(orderIdList.loadOrderID() != null) {
			LoggingUtil.trace("UniqueOrderIDSerializeDAO load");
			UniqueOrderIDCounter.getUniqeidcounter().clear();
			UniqueOrderIDCounter.getUniqeidcounter().addAll(orderIdList.loadOrderID());
		}
		
		WebServiceImpl web = new WebServiceImpl();

		boolean keepGoing = true;

		do {
			LoggingUtil.trace("Do Loop in Main - Start");
			
			
			String screen = web.initialScreen();
			

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
					orderIdList.saveOrderId(UniqueOrderIDCounter.getUniqeidcounter());
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
					orderIdList.saveOrderId(UniqueOrderIDCounter.getUniqeidcounter());
					// go to top of loop
					continue;
				}
			}


		} while (keepGoing);
		


	}

}
