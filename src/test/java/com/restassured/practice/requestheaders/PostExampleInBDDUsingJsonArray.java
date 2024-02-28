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

public class PostExampleInBDDUsingJsonArray {

	@org.testng.annotations.BeforeClass
	public void BeforeClass() {
		 ResponseSpecification customResponse;

		RequestSpecBuilder requstSpecBuilder = new RequestSpecBuilder();
		requstSpecBuilder.setBaseUri("https://88b35c90-6ec9-4914-9c80-faaae04db90a.mock.pstmn.io/post")
				.addHeader("x-mock-match-request-body", "true")
			//	.setConfig(config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
				.setContentType("application/json; charset=utf-8").log(LogDetail.ALL);
		
		RestAssured.requestSpecification = requstSpecBuilder.build();

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		customResponse = responseSpecBuilder.build();

	}

	@Test
	public void vaidatePostReqPayloadUsingArrayList() {
		HashMap<String, String> obj1 = new HashMap<String, String>();

		obj1.put("id", "5001");
		obj1.put("type", "None");

		HashMap<String, String> obj2 = new HashMap<String, String>();
		obj2.put("id", "5002");
		obj2.put("type", "Glazed");
		List<HashMap<String,String>> arrayList= new ArrayList<HashMap<String, String>>();
		arrayList.add(obj1);
		arrayList.add(obj2);

		given().body(arrayList).when().post().then().assertThat().body("Message",

				equalTo("Success"));

	}

}
