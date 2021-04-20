package com.tests;

import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers.*; 
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pojo.Data;
import com.pojo.Favfood;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;//ctrl+shift+0 -->remove the unused imports

public class Runner {
	private static final SecureRandom random= new SecureRandom();

	//start with understanding all the concepts
	//then we will create the framework

	//Static imports



	//we will create our own fake server --> Use postman
	//json-server build my fake json server in my localhost

	//@Test
	public void getTest() {
		Response response = given().get("http://localhost:3000/employees");

		Headers a = response.getHeaders();

		for(Header header: a) {
			System.out.println(header.getName() + ":"+header.getValue());
		}

		String actual =response.jsonPath().getString("[1].email");
		Assert.assertEquals(actual, "steve@codingthesmartway.com");

	}

	//@Test
	public void postTest() {
		//passing body as simple string

		Response response=	given().header("Content-Type","application/json").log()
				.body().body("{\r\n"
						+ "    \"id\": 6,\r\n"
						+ "    \"first_name\": \"Sebastian\",\r\n"
						+ "    \"last_name\": \"Eschweiler\",\r\n"
						+ "    \"email\": \"sebastian@codingthesmartway.com\"\r\n"
						+ "  }").post("http://localhost:3000/employees");

		response.prettyPrint();
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
	}


	//@Test
	public void postTestFromJson() {
		Response res= given().header("Content-Type","application/json").log().all()
				.body(new File("test.json")).post("http://localhost:3000/employees");

		res.prettyPrint();
	}

	//@Test
	public void postTestReadJsonConvertToString() throws IOException {
		//System.out.println(generateRandomNumericString(3));
		 String jsonbody = getJsonFileAsString("test.json"); //req body is having
		// nested json and you have to change a particular value alone everytime
		  Response res= given().header("Content-Type","application/json").log().all()
		 .body(jsonbody.
				 replace("14", generateRandomNumericString(2)).replace("testing", generateRandomString(6))).post("http://localhost:3000/employees");
		 res.prettyPrint(); 
	}
	
	//@Test
	public void postRequestUsingMap() {
		// Jsonobject -->{ } --- > HashMap 
		//Json arrays-->[ ] --> ArrayList
		//Jackson databind in the classpath
		
		Map<String,Object> requestmap = new HashMap<>();
		requestmap.put("id", generateRandomNumericString(2));
		requestmap.put("first_name", generateRandomString(4)); //ASDF ["ASDF","Amuthan"]
		requestmap.put("last_name", generateRandomString(4));
		requestmap.put("email", generateRandomString(7)+"@gmail.com");
		requestmap.put("first_name", "Amuthan");
		
		List<String> array = new ArrayList<>();
		array.add("tester");
		array.add("trainer");
		array.add("farmer");
		
		requestmap.put("job", array);
		
		Map<String,String> innerjson = new HashMap<>();
		innerjson.put("breakfast","dosa");
		innerjson.put("lunch","rice");
	
		
		requestmap.put("favfood", innerjson);
			
		Response response = given().header("Content-Type","application/json").log().all()
			.body(requestmap).post("http://localhost:3000/employees");
		
		response.prettyPrint();
		System.out.println(response.getStatusLine());
	}
	
	@Test
	public void postRequestUsingJSONLibrary() {
		JSONObject outerobj = new JSONObject(); //Same as your hashmap
		
		outerobj.put("id", generateRandomNumericString(2));
		outerobj.put("first_name", generateRandomString(4)); //ASDF ["ASDF","Amuthan"]
		outerobj.put("last_name", generateRandomString(4));
		outerobj.put("email", generateRandomString(7)+"@gmail.com");
		JSONArray array = new JSONArray();
		array.put("tester");
		array.put("trainer");
		array.put("farmer");
		
		outerobj.put("job", array);
		
		JSONObject innerobj = new JSONObject();
		innerobj.put("breakfast", "dosa");
		innerobj.put("lunch", "rice");
		
		outerobj.put("favfood", innerobj);
		
		Map<String, Object> a = outerobj.toMap();
		
	Response response = 	given().header("Content-Type","application/json").log().all()
				.body(a).post("http://localhost:3000/employees");
				
	
		Data b = response.as(Data.class);
		

		
		
	}
	
	//@Test
	public void postRequestUsingPojo() {
		Data dataobj = new Data();
		dataobj.setEmail(generateRandomString(7)+"@gmail.com");
		dataobj.setFirst_name(generateRandomString(4));
		dataobj.setLast_name(generateRandomString(4));
		dataobj.setId(Integer.parseInt(generateRandomNumericString(2)));
		
		
		dataobj.setJob(Arrays.asList("trainer","tester"));
		
		Favfood fav = new Favfood();
		fav.setBreakfast("idly");
		fav.setLunch("rice");
		fav.setDinner(Arrays.asList("chapthi","chicken"));
		dataobj.setFavfood(fav);
		
		System.out.println(dataobj.toString());
		
		/*
		 * Response response =
		 * given().header("Content-Type","application/json").log().all()
		 * .body(dataobj).post("http://localhost:3000/employees");
		 * response.prettyPrint();
		 */
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	private String getJsonFileAsString(String location) throws IOException{

		return new String(Files.readAllBytes(Paths.get(location)));
	}

	public static String generateRandomNumericString(int length) {
		String textnumber ="0123456789";
		StringBuilder sb = new StringBuilder(length);
		for( int i = 0; i < length; i++ ) 
			sb.append( textnumber.charAt( random.nextInt(textnumber.length()) ) );
		return sb.toString();

	}
	
	public static String generateRandomString(int length) {

		String text ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder(length);
		for( int i = 0; i < length; i++ ) 
			sb.append( text.charAt( random.nextInt(text.length()) ) );
		return sb.toString();

	}
	
	//Hashmap , Pojo, external libraries JSON
	














}
