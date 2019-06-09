package com.donald.users;

import java.util.ArrayList;
import java.util.List;

public class UniqueOrderIDCounter {
	
	private static final List<UniqueOrderID> uniqeIdCounter = new ArrayList<>();

	public static List<UniqueOrderID> getUniqeidcounter() {
		return uniqeIdCounter;
	}



}
