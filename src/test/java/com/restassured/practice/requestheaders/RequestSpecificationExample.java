package com.restassured.practice.requestheaders;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import org.hamcrest.core.IsEqual;

public class RequestSpecificationExample {

	// Request specification is an interface
	// There is a default request specification called as requestSpecification
	
	ResponseSpecification responseSpecification;

	@BeforeClass
	public void beforeClass() {
//		reqSpec = given().baseUri("https://api.postman.com").header("x-api-key",
//				"PMAK-659d95664380a40bdd208044-da49c168ef3b7b6f9382a2e5c32f0d2eb2")
//				.log().all();
		
		RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
		reqSpecBuilder.setBaseUri("https://api.postman.com");
		reqSpecBuilder.addHeader("x-api-key",
				"PMAK-659d95664380a40bdd208044-da49c168ef3b7b6f9382a2e5c32f0d2eb2");
		reqSpecBuilder.addHeader("dummyHeader","dummy Value");
		reqSpecBuilder.log(LogDetail.HEADERS);
		RestAssured.requestSpecification = reqSpecBuilder.build();
		responseSpecification = RestAssured.expect().statusCode(200).and().contentType(ContentType.JSON);
		}

	@Test
	public void reqSpecificationExample() {

		get("/workspaces").then().log().headers().assertThat().statusCode(200).log().all();

	}

	@Test
	public void validateResponseBody() {

		Response response = get("/workspaces").then().extract().response();
		//assertThat(response.statusCode(), equalTo(200));
		assertThat(response.path("workspaces[0].name").toString(), equalTo("My Workspace"));

	}
	/** This method is used to query the request specification and retrive the values 
	 * 
	 */
	
	@Test
	public void queryExample() {
		
		QueryableRequestSpecification queryReq = SpecificationQuerier.query(requestSpecification);
		System.out.println(queryReq.getBaseUri());
		System.out.println(queryReq.getHeaders());
	}
	
	

}
