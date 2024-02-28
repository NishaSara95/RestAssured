package com.restassured.practice.requestheaders;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.hamcrest.text.MatchesPattern;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.w3c.dom.UserDataHandler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class PostExampleInBDDComplexJsonArray {

	@org.testng.annotations.BeforeClass
	public void BeforeClass() {
		 ResponseSpecification customResponse;

		RequestSpecBuilder requstSpecBuilder = new RequestSpecBuilder();
		requstSpecBuilder.setBaseUri("https://88b35c90-6ec9-4914-9c80-faaae04db90a.mock.pstmn.io")
				.addHeader("x-mock-match-request-body", "true")
			//	.setConfig(config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
				.setContentType("application/json; charset =utf-8").log(LogDetail.ALL);
		
		RestAssured.requestSpecification = requstSpecBuilder.build();

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		customResponse = responseSpecBuilder.build();

	}

	@Test
	public void vaidatePostReqPayloadForComplexJson() {

		List<Integer> idArrayList= new ArrayList<Integer>();
		idArrayList.add(2);
		idArrayList.add(3);
		HashMap<String, Object> batterHashMap2 = new HashMap<String, Object>();
		batterHashMap2.put("id", idArrayList);
		batterHashMap2.put("type", "Chocolate");

		HashMap<String, Object> batterHashMap1 = new HashMap<String, Object>();
		batterHashMap1.put("id", "1001");
		batterHashMap1.put("type", "Regular");
		
		List<HashMap<String, Object>> batterArrayList= new ArrayList<HashMap<String, Object>>();
		batterArrayList.add(batterHashMap1);
		batterArrayList.add(batterHashMap2);

		
		List<String> typeArrayList= new ArrayList<String>();
		typeArrayList.add("type1");
		typeArrayList.add("type2");
		HashMap<String, Object> toppingHashMap2 = new HashMap<String, Object>();
		toppingHashMap2.put("id", "5005");
		toppingHashMap2.put("type", typeArrayList);
		

		HashMap<String, Object> toppingHashMap1 = new HashMap<String, Object>();
		toppingHashMap1.put("id", "5001");
		toppingHashMap1.put("type", "None");
		List<HashMap<String, Object>> toppingArrayList= new ArrayList<HashMap<String, Object>>();
		toppingArrayList.add(toppingHashMap1);
		toppingArrayList.add(toppingHashMap2);
		
		HashMap<String, Object> battersMap= new HashMap<String, Object>();
		battersMap.put("batter", batterArrayList);
		
		HashMap<String, Object> mainHashMap= new HashMap<String, Object>();
		mainHashMap.put("id", "0001");
		mainHashMap.put("type", "donut");
		mainHashMap.put("name", "Cake");
		mainHashMap.put("ppu", 0.55);
		mainHashMap.put("batters", battersMap);
		mainHashMap.put("topping", toppingArrayList);



		given().body(mainHashMap).when().post("/postComplexJson").then().assertThat().body("Message",

				equalTo("Success"));

	}

}
