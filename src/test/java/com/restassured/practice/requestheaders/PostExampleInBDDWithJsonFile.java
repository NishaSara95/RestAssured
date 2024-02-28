package com.restassured.practice.requestheaders;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
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
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class PostExampleInBDDWithJsonFile {

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
	public void vaidatePostReqUsingFile() {

		File file = new File("src/main/resources/createResourcePayload.json");
		given().body(file).when().post("/workspaces").then().assertThat().body("workspace.name",
				equalTo("My Third Workspace"), "workspace.id", Matchers.matchesPattern("^[a-z0-9-]{36}$"));

	}
	
	@Test
	public void vaidatePostReqAsMap() {
		Map<String, Object> masterMap = new HashMap<String, Object>();
		Map<String, Object> nestObj = new HashMap<String, Object>();
		nestObj.put("name", "My fourth Workspace");
		nestObj.put("type", "personal");
		nestObj.put("description", "Updated For fourth learning");
		masterMap.put("workspace", nestObj);
		
		
		given().body(masterMap).when().post("/workspaces").then().assertThat().body("workspace.name",
				equalTo("My fourth Workspace"), "workspace.id", Matchers.matchesPattern("^[a-z0-9-]{36}$"));

	}

	
}
