package com.donald.dao;

import java.util.List;


import com.donald.users.UniqueOrderID;

public interface UniqueOrderIdDAO {
	
	public void saveOrderId(List<UniqueOrderID> uniqueOrderID);

	public List<UniqueOrderID> loadOrderID();
}
