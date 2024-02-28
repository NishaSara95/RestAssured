package com.restassured.practice.requestheaders;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class RequestParam {

	@Test

	public void queryParamSingleParameter() {

		given().baseUri("https://postman-echo.com").queryParam("foo1", "bar1").when().get("/get").then().statusCode(200).log().all();

	}
	@Test
	public void multiValuequeryParam() {

		given().baseUri("https://postman-echo.com").queryParam("foo1", "bar1;bar2;bar3").when().get("/get").then().statusCode(200).log().all();

	}
	
	
	@Test
	public void queryParamMultipleParameter() {
		
		HashMap< String , String> map = new HashMap<String, String>();
		map.put("foo1", "bar1");
		map.put("foo2", "bar2");
		given().baseUri("https://postman-echo.com").queryParams(map).when().get("/get").then().statusCode(200).log().all();

	}
	@Test
	public void pathParameter() {
		
		given().baseUri("https://reqres.in").pathParam("usersId", "2").
		when().get("/api/users/{usersId}").then().assertThat().statusCode(200).log().all();
		
		
	}
	
	@Test
	public void automateFormData() {
		
		given().baseUri("https://postman-echo.com").multiPart("foo1","bar1").post("/post").
		then().assertThat().statusCode(200).log().all();
		
		
	}
	
	
	@Test
	public void automateFormDataUploadingFile() {
		
		
		given().baseUri("https://postman-echo.com").
		multiPart("file", new File("src/main/resources/temp.txt")).
		multiPart("attribute", "{\"name\":\"temp.txt\",\"parent\":{\"id\",\"12345\"}}","application/json").
		multiPart("foo1","bar1").post("/post").
		then().assertThat().statusCode(200).log().all();
	}
	
	
	@Test
	public void automateDownloadFile() throws IOException {
		InputStream is = given().baseUri("https://raw.githubusercontent.com").
		post("/appium/appium/master/packages/appium/sample-code/apps/ApiDemos-debug.apk").
		then().log().all().extract().response().asInputStream();
		OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
		byte[] bytes = new byte[is.available()];
		is.read(bytes);
		os.write(bytes);
		os.close();
	}

}
