package com.donald.users;

import java.io.Serializable;

public class UniqueOrderID implements Serializable {

	private Integer count;

	public UniqueOrderID(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
