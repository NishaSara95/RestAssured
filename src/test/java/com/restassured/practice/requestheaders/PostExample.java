package com.restassured.practice.requestheaders;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import org.hamcrest.Matchers;
import org.hamcrest.text.MatchesPattern;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostExample {

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
	public void automatePostRequestUsingBDD() {

		String payload = "{\r\n" + "    \"workspace\": {\r\n" + "        \"name\": \"My Example Workspace1\",\r\n"
				+ "        \"type\": \"personal\",\r\n" + "        \"description\": \"For learning\"\r\n" + "    }\r\n"
				+ "}";
		given().body(payload).when().post("/workspaces").then().assertThat().body("workspace.name",
				equalTo("My Example Workspace1"), "workspace.id", Matchers.matchesPattern("^[a-z0-9-]{36}$"));

	}

	@Test
	public void automatePostRequestUsingNonBDD() {

		String payload = "{\r\n"
				+ "    \"workspace\": {\r\n"
				+ "        \"name\": \"My Example Workspace3\",\r\n"
				+ "        \"type\": \"personal\",\r\n"
				+ "        \"description\": \"For learning\"\r\n"
				+ "    }\r\n"
				+ "}";
		Response response = with().body(payload).post("/workspaces");
		assertThat(response.<String>path("workspace.name"), equalTo("My Example Workspace3"));
		assertThat(response.<String>path("workpace.id"), Matchers.matchesPattern("^[a-z0-9-]{36}$"));

	}

}
