package com.gettersetters;

import java.util.Objects;

public class Student {
	
	private int id;
	private String name;
	
	public int getId() {
		if(id==0) {
			return 15;
		}
		return id;
	}
	public void setId(int id) {
		
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(Objects.isNull(name)) {
			this.name="Amuthan";
		}else {
		this.name = name;
		}
	}
	
	
	

}
