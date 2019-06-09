package com.donald.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.donald.users.Customer;
import com.donald.util.LoggingUtil;

public class CustomerListSerializeDAO implements CustomerListDAO {

	@Override
	public void saveCustomerList(List<Customer> customerList) {

		try {
			FileOutputStream fos = new FileOutputStream("CustomerListData");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(customerList);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			LoggingUtil.warn("IOException called by CustomerListSerializeDAO saveCustomerList();");
		}

	}

	@Override
	public List<Customer> loadCustomerList() {
		List<Customer> customerList = null;

		try {
			FileInputStream fis = new FileInputStream("CustomerListData");
			ObjectInputStream ois = new ObjectInputStream(fis);

			// loads list
			customerList = (ArrayList) ois.readObject();

			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			LoggingUtil.warn("FileNotFoundException called by CustomerListSerializeDAO loadCustomerList();");
			return null;
		} catch (IOException ioe) {
			LoggingUtil.warn("IOException called by CustomerListSerializeDAO loadCustomerList();");
		} catch (ClassNotFoundException c) {
			LoggingUtil.warn("ClassNotFoundException called by CustomerListSerializeDAO loadCustomerList();");
		}

		return customerList;
	}

}
