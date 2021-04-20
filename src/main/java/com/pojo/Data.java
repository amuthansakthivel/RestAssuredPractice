package com.pojo;

import java.util.List;

public class Data {

	//plain old java object
	// json object --> { } --> Create a new class 

	//Jackson needs getter and setter to convert to json
	//Lombok 

	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private Favfood favfood;
	private List<String> job;

	public List<String> getJob() {
		return job;
	}
	public void setJob(List<String> job) {
		this.job = job;
	}
	public Favfood getFavfood() {
		return favfood;
	}
	public void setFavfood(Favfood favfood) {
		this.favfood = favfood;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Data [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", favfood=" + favfood + ", job=" + job + "]";
	}

}
