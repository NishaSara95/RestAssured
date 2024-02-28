package com.restassured.practice.requestheaders;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class RequestHeaderExample {
	
	@Test
	public void headerExample() {
		
		given().baseUri("https://88b35c90-6ec9-4914-9c80-faaae04db90a.mock.pstmn.io/get").
		when().get().then().assertThat().statusCode(200);
		
	}

}
