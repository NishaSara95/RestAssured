package com.rsapractice.basics;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import payloads.Payload;

public class AutomatePostMap {

	String idValue = "";

	@Test(priority = 1)
	public void postMethodAddMap() {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").log().all().header("Content-Type", "application/json")
				.body(Payload.Payload()).when().post("/maps/api/place/add/json").then().assertThat()
				.body("scope", equalTo("APP"), "status", equalTo("OK")).statusCode(200)
				.header("Server", equalTo("Apache/2.4.52 (Ubuntu)")).log().all().extract().response().asString();
		System.out.println("Response is " + response);
		JsonPath jsonPath = new JsonPath(response); // Json Path is a class used to convert string into Json
		idValue = jsonPath.getString("place_id");
		System.out.println("value" + idValue);

	}
	
	// Content in the file converts to String -> Content converts to Bytes first -> Bytes get converted to String 
	
	@Test(priority = 1)
	public void postMethodAddMapUsingJsonFile() throws IOException {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").log().all().header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Windows 11\\Downloads\\addPlace.json")))).when().post("/maps/api/place/add/json").then().assertThat()
				.body("scope", equalTo("APP"), "status", equalTo("OK")).statusCode(200)
				.header("Server", equalTo("Apache/2.4.52 (Ubuntu)")).log().all().extract().response().asString();
		System.out.println("Response is " + response);
		JsonPath jsonPath = new JsonPath(response); // Json Path is a class used to convert string into Json
		idValue = jsonPath.getString("place_id");
		System.out.println("value" + idValue);

	}

	@Test(priority = 2)
	public void putMethodMap() {

		String payload = "{\r\n" + "\"place_id\":\"" + idValue + "\",\r\n" + "\"address\":\"Cottesmore avenue, United kingdom\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n" + "}";
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().queryParam("place_id", idValue).queryParam("key", "qaclick123").log().all()
				.header("Content-Type", "application/json").body(payload).log().all().when()
				.put("/maps/api/place/update/json").then().assertThat()
				.body("msg", equalTo("Address successfully updated")).log().all();

	}

	@Test(priority = 3)
	public void getMethodMap() {
		
		String newAddress="Cottesmore avenue, United kingdom";

		RestAssured.baseURI = "https://rahulshettyacademy.com";
	 String response = given().queryParams("key", "qaclick123", "place_id", idValue).header("Content-Type", "application/json").log()
				.all().when().get("/maps/api/place/get/json").then().extract().response().asString();
	 JsonPath json = new JsonPath(response);
	 json.getString("address").equals(newAddress);
	 System.out.println("Updated address"+json.getString("address"));
	 Assert.assertEquals(json.getString("address"), newAddress);
	}
}
