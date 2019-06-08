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
			ioe.printStackTrace();
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
			e.printStackTrace();
			return null;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
		}

		for (Customer customer : customerList) {
			System.out.println("username" + customer.getUsername() + "password" + customer.getPassword() + "ID"
					+ customer.getCustomerID());
		}

		return customerList;
	}

}
