package com.rsapractice.basics;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restassured.practice.pojoClasses.AddPlacePojo;
import com.restassured.practice.pojoClasses.CourseDetailsPojo;
import com.restassured.practice.pojoClasses.Location;

import com.restassured.practice.pojoClasses.WebAutomationPojo;

import CommonMethods.CommonFunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SerializationMapExample {

	RequestSpecification req;
	ResponseSpecification res;

	@BeforeTest
	public void beforeTest() {

		req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.log(LogDetail.ALL).build();
		res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).log(LogDetail.ALL)
				.build();

	}

	@Test
	public void addMap() {
		// RestAssured.baseURI= "https://rahulshettyacademy.com";

		AddPlacePojo addPlace = new AddPlacePojo();
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		List<String> typelist = new ArrayList<String>();
		typelist.add("shoe park");
		typelist.add("shop");
		addPlace.setAccuracy(50);
		addPlace.setName("Frontline house");
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setAddress("29, side layout, cohen 09");
		addPlace.setWebsite("http://google.com");
		addPlace.setLanguage("French-IN");
		addPlace.setLocation(loc);
		addPlace.setTypes(typelist);

		RequestSpecification request = given().spec(req);

		String response = request.when().post("/maps/api/place/add/json").then().spec(res).extract().response()
				.asString();
		System.out.println(response);
	}

}
