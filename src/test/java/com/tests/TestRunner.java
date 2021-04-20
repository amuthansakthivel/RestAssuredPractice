package com.tests;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pojo.Person;
import com.pojo.PersonBuilder;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TestRunner {
	
	//@Test
	public void postReqUsingPojo() {
		
		//Builder design pattern --> readability 
		
		Person p1 = new Person("abcd","sdsc",56); //What if there are 15 parameters
											// I dont want to send some value in the request(lname)
	
		Person p2= new Person("sdfs","sdfsdf",45);
		
		
		List<Person> list = Arrays.asList(p1,p2);
		
		Map<String,String> headermap = new HashMap<>();
		headermap.put("Content-Type", "application/json");
		
		given().headers(headermap).body(list).post("http://localhost:3000/employees").prettyPrint();
	
	}
	
	
	
	@Test
	public void postReqUsingPojoUsingBuilder() {
		
		//Builder design pattern --> readability 
		
		Person p1 = new PersonBuilder()
				.setFirstName("dfgv")
				.setAge(28)
				.setLastName("sfd")
				.getPerson();
	
		Person p2 = new PersonBuilder()
					.setAge(35)
					.setFirstName("sdfs")
					.getPerson();
		
		
		List<Person> list = Arrays.asList(p1,p2);
		
		Map<String,String> headermap = new HashMap<>();
		headermap.put("Content-Type", "application/json");
		
	Response response = 	given().headers(headermap).body(list).post("http://localhost:3000/employees");
	response.prettyPrint();
	List<Map<String,Object>> a = response.jsonPath().getList("$");
	for(Map<String, Object> temp : a) {
		System.out.println(temp.get("age"));
	}
	Assert.assertEquals(a.size(), 2);
	Assert.assertEquals(response.jsonPath().getString("[0].lastName"), "sfd");
	
	int time = (int)response.getTime();
	
	Assert.assertTrue(time>200);
	Assert.assertTrue(time<1000);
	Assert.assertTrue(time!=0);
	
	System.out.println(response.getHeader("Content-Type"));
	Map<String, String> cookies = response.getCookies();
	
	cookies.forEach((k,v)->System.out.println("key :"+k+ "value :"+v));
	
	Headers headers = response.getHeaders();
	for(Header header : headers) {
		System.out.println(header.getName());
		System.out.println(header.getValue());
	}
	
	
	
	
	
	
	
	
	
	}

}
