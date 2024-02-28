package com.restassured.practice.requestheaders;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
public class FiltersExample {
	
	
	
	@Test
	public void filtersExampleInConsole() {
		
		given().baseUri("https://postman-echo.com").filter(new RequestLoggingFilter(LogDetail.BODY)).filter(new ResponseLoggingFilter(LogDetail.HEADERS)).
		
		
		when().get("/get").then().assertThat().statusCode(200);
	}
	
	@Test
	public void printLogsInFile() throws FileNotFoundException {
		
		PrintStream fos= new PrintStream("resource.log");
		given().baseUri("https://postman-echo.com").filter(new RequestLoggingFilter(LogDetail.ALL, fos)).
				filter(new ResponseLoggingFilter(LogDetail.ALL, fos)).
		
		
		when().get("/get").then().assertThat().statusCode(200);
	}

}
