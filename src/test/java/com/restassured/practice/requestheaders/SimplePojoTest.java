package com.restassured.practice.requestheaders;


import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restassured.practice.pojoClasses.SimplePojo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class SimplePojoTest {
	
	ResponseSpecification responseSpecification;
	@org.testng.annotations.BeforeClass
	public void BeforeClass() {

		RequestSpecBuilder requstSpecBuilder = new RequestSpecBuilder();
		requstSpecBuilder.setBaseUri("https://88b35c90-6ec9-4914-9c80-faaae04db90a.mock.pstmn.io")
				.setContentType(ContentType.JSON).log(LogDetail.ALL);

		RestAssured.requestSpecification = requstSpecBuilder.build();

		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		responseSpecification = responseSpecBuilder.build();

	}
	
	
	/** Serialization using simple Pojo class converting java objects to Json objects is serialization
	 * 
	 */
	@Test
	public void simplePojoExample() {
		
		//SimplePojo simple = new SimplePojo("Value1", "Value2");
		SimplePojo payload = new SimplePojo();
		payload.setKey1("value1");
		payload.setKey2("value2");

//		String payload= "{\r\n"
//				+ "    \"Key1\" : \"Value1\",\r\n"
//				+ "    \"Key2\" :\"Value2\"\r\n"
//				+ "}";
		
		
		
		given().body(payload).when().
		post("/postSimplePojoMock").then().spec(responseSpecification).assertThat().statusCode(200).assertThat().body("key1", equalTo(payload.getKey1()),"key2", equalTo(payload.getKey2()));

	}

	/** DeSerialization using simple Pojo class (converting json objects to Java objects)
	 * @throws JsonProcessingException 
	 * 
	 */

	@Test
	public void deserailizeExample() throws JsonProcessingException {

		SimplePojo simplePojo = new SimplePojo("value1","value2");
		SimplePojo deserializedJavaObj = given().when().body(simplePojo).post("/postSimplePojoMock").then().spec(responseSpecification).extract().response().as(SimplePojo.class);
	
		ObjectMapper objectMapper = new ObjectMapper();
		String simplePojoAsStr = objectMapper.writeValueAsString(simplePojo);

		String deserializedJavaObjToStr = objectMapper.writeValueAsString(deserializedJavaObj);
		assertThat(objectMapper.readTree(deserializedJavaObjToStr), equalTo(objectMapper.readTree(simplePojoAsStr) ));
		
}
	
}

