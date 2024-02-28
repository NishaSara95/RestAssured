package com.restassured.practice.requestheaders;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

public class PutExample {

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
	public void automatPutUsingBdd() {
		
		
	
	String payload = "{\r\n" + "    \"workspace\": {\r\n" + "        \"name\": \"My Example Workspace New2\",\r\n"
			+ "        \"type\": \"personal\",\r\n" + "        \"description\": \"Updated For learning\"\r\n" + "    }\r\n"
			+ "}";
	
	String workspaceId="685526ea-ca71-47cb-aaa5-4e20a5981085";
	given().
			body(payload).
			pathParam("workspaceId", workspaceId);
			
	when().

			put("/workspaces/{workspaceId}").
	then().
	assertThat().body("workspace.name", equalTo("My Example Workspace New2"));
	}

}
