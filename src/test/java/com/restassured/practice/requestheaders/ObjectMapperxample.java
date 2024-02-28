package com.restassured.practice.requestheaders;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
public class ObjectMapperxample {
	
	
	@org.testng.annotations.BeforeClass
	public void BeforeClass() {

		RequestSpecBuilder requstSpecBuilder = new RequestSpecBuilder();
		requstSpecBuilder.setBaseUri("https://api.postman.com")
				.addHeader("x-api-key", "PMAK-659d95664380a40bdd208044-da49c168ef3b7b6f9382a2e5c32f0d2eb2")
				.setContentType(ContentType.JSON).log(LogDetail.ALL);

		RestAssured.requestSpecification = requstSpecBuilder.build();

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		RestAssured.responseSpecification = responseSpecBuilder.build();

	}
	@Test
	public void objctMapperExample() throws JsonProcessingException {

		HashMap<String, Object> mainObject = new HashMap<String, Object>();
		
		HashMap<String, String> nestedObject = new HashMap<String, String>();
		
		nestedObject.put("name", "Serializer learning Workspace");
		nestedObject.put("type", "personal");

		nestedObject.put("description", "Updated For learning");
		mainObject.put("workspace", nestedObject);
		
		ObjectMapper obj = new ObjectMapper();
		String writeValueAsString = obj.writeValueAsString(mainObject);
		given().
		body(writeValueAsString).
		when().post("/workspaces").
		then().assertThat().body("workspace.name", equalTo("Serializer learning Workspace"));
	}
	
	
	
	
}
