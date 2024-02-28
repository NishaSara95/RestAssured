package com.restassured.practice.requestheaders;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecificationExample {

	// ResponseSpecification responseSpecification;

	@BeforeClass
	public void beforeClass() {

		// This allows to build the request specification
		RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();

		reqSpecBuilder.setBaseUri("https://api.postman.com");
		reqSpecBuilder.addHeader("x-api-key", "PMAK-659d95664380a40bdd208044-da49c168ef3b7b6f9382a2e5c32f0d2eb2");
		reqSpecBuilder.addHeader("dummyHeader", "dummy Value");
		reqSpecBuilder.log(LogDetail.HEADERS);
		// To specify a default request specification we use inbuilt requestSpecificaton
		// method
		RestAssured.requestSpecification = reqSpecBuilder.build();
//		RestAssured.responseSpecification = RestAssured.expect().statusCode(200).and().contentType(ContentType.JSON)
//				.logDetail(LogDetail.ALL);

		ResponseSpecBuilder resSpecBuilder = new ResponseSpecBuilder();

		resSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		// To specify the default Response specification
		RestAssured.responseSpecification = resSpecBuilder.build();

	}

	@Test

	public void validateStatusCode() {

		get("/workspaces").then();

	}

	@Test

	public void validateResponseBody() {

		Response response = get("/workspaces").then().

				extract().response();

		assertThat(response.path("workspaces[0].name").toString(), equalTo("My Workspace"));

	}

}
