package com.restassured.practice.requestheaders;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class JsonSchemaValidation {

	@Test
	public void jsonSchemaValidationExample() {
		given().baseUri("https://postman-echo.com").log().all().
		when().get("/get").then().log().all().assertThat().statusCode(200).body(matchesJsonSchemaInClasspath("EchoGet.json"));
	}
}
