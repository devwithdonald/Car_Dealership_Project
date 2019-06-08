package com.donald.users;

import java.io.Serializable;

public class CarId implements Serializable{

	private Integer count;
	
	public CarId(Integer count){
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
}
