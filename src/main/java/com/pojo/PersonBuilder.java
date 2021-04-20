package com.pojo;

public class PersonBuilder {
	
	//it is going to help us to build Person objects
	
	private String firstName;
	private String lastName;
	private int age;
	
	public PersonBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	public PersonBuilder setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	public PersonBuilder setAge(int age) {
		this.age = age;
		return this;
	}
	
	public Person getPerson() {
		return new Person(this.firstName,this.lastName,this.age);
	}

}
