package com.restassured.practice.requestheaders;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
public class ListToJsonArayExampe {
	
	
	@org.testng.annotations.BeforeClass
	public void BeforeClass() {

		RequestSpecBuilder requstSpecBuilder = new RequestSpecBuilder();
		requstSpecBuilder.setBaseUri("https://88b35c90-6ec9-4914-9c80-faaae04db90a.mock.pstmn.io")
				.setContentType(ContentType.JSON).log(LogDetail.ALL);

		RestAssured.requestSpecification = requstSpecBuilder.build();

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		RestAssured.responseSpecification = responseSpecBuilder.build();

	}
	@Test
	public void ListToJsonArrayExample() throws JsonProcessingException {

		
		HashMap<String, String> mapObject1 = new HashMap<String, String>();
		HashMap<String, String> mapObject2 = new HashMap<String, String>();

		
		mapObject1.put("id", "5001");
		mapObject1.put("type", "None");

		mapObject2.put("id", "5002");
		mapObject2.put("type", "Glazed");
		
		List<HashMap<String, String> >list = new ArrayList<HashMap<String,String>>();
		
		list.add(mapObject1);			
		list.add(mapObject2);
		
//		ObjectMapper obj = new ObjectMapper();
//		String writeValueAsString = obj.writeValueAsString(mainObject);
		given().
		body(list).
		when().post("/post").
		then().assertThat().body("Message", equalTo("Success"));
	}
	
	
	
	
}
