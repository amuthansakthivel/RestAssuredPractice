package com.tests;

import static com.dummy.C.sub; //io.restassured.RestAssured.given
import static com.tests.A.*;

public class B {
	
	//if you are accessing static members from another class, you can make static import

	public static void main(String[] args) {
		add();
		mul();
		sub();
	}

}
