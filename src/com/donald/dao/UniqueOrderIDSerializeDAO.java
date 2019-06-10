package com.donald.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.donald.users.CarId;
import com.donald.users.UniqueOrderID;
import com.donald.util.LoggingUtil;

public class UniqueOrderIDSerializeDAO implements UniqueOrderIdDAO {

	@Override
	public void saveOrderId(List<UniqueOrderID> uniqueOrderID) {

		try {
			FileOutputStream fos = new FileOutputStream("OrderIDData");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(uniqueOrderID);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			LoggingUtil.error("IOException called by UniqueOrderIdSerializeDAO saveOrderId();");
		}

	}

	@Override
	public List<UniqueOrderID> loadOrderID() {
		List<UniqueOrderID> uniqueIdList = null;

		try {
			FileInputStream fis = new FileInputStream("OrderIDData");
			ObjectInputStream ois = new ObjectInputStream(fis);

			// loads list
			uniqueIdList = (ArrayList) ois.readObject();

			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			LoggingUtil.error("FileNotFoundException called by UniqueOrderIdSerializeDAO loadOrderID();");
			return null;
		} catch (IOException ioe) {
			LoggingUtil.error("IOException called by UniqueOrderIdSerializeDAO loadOrderID();");
		} catch (ClassNotFoundException c) {
			LoggingUtil.error("ClassNotFoundException called by UniqueOrderIdSerializeDAO loadOrderID();");

		}

		return uniqueIdList;
	}

}
