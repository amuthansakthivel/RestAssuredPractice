package com.pojo;

import java.util.List;

public class Favfood {



	private String breakfast;
	private String lunch;
	private List<String> dinner;
	
	@Override
	public String toString() {
		return "FavFood [breakfast=" + breakfast + ", lunch=" + lunch + ", dinner=" + dinner + "]";
	}
	public List<String> getDinner() {
		return dinner;
	}
	public void setDinner(List<String> dinner) {
		this.dinner = dinner;
	}
	public String getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}
	public String getLunch() {
		return lunch;
	}
	public void setLunch(String lunch) {
		this.lunch = lunch;
	}
	
	

}
