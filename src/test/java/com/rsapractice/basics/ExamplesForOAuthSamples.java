package com.rsapractice.basics;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.restassured.practice.pojoClasses.CourseDetailsPojo;
import com.restassured.practice.pojoClasses.WebAutomationPojo;

import CommonMethods.CommonFunctions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class ExamplesForOAuthSamples {

	
	@Test
	public void getCoureDetailsUsingAccessToken() {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().header("content_Type", "multipart/form-data")
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").when().post("/oauthapi/oauth2/resourceOwner/token").then().assertThat()
				.statusCode(200).log().all().extract().response().asString();

		JsonPath jsonResponse = CommonFunctions.stringRawToJson(response);

		String accessToken = jsonResponse.get("access_token").toString();
		System.out.println(accessToken);

		CourseDetailsPojo cd = given().queryParam("access_token", accessToken).when().get("/oauthapi/getCourseDetails")
				.as(CourseDetailsPojo.class);

		System.out.println(cd.getInstructor());
		System.out.println(cd.getLinkedIn());
		System.out.println(cd.getExpertise());

		String cypressTitle = cd.getCourses().getWebAutomation().get(1).getCourseTitle();
		System.out.println("cypressTitle" + cypressTitle);

		// To get Course Title of 'Cypress'
		int WebAutomationsize = cd.getCourses().getWebAutomation().size();
		for (int i = 0; i < WebAutomationsize; i++) {
			String courseTitle = cd.getCourses().getWebAutomation().get(i).getCourseTitle();
			if (courseTitle.equalsIgnoreCase("Cypress")) {
				String CypressCourprice = cd.getCourses().getWebAutomation().get(i).getPrice();
				System.out.println("CypressCourseprice"+CypressCourprice.toString());
				break;
			}
		}
		// To get price of all courses
		for (int i = 0; i < WebAutomationsize; i++) {
			String webAutomationprice = cd.getCourses().getWebAutomation().get(i).getPrice();
			int intwebAutomationPrice = Integer.parseInt(webAutomationprice);
			intwebAutomationPrice += intwebAutomationPrice;
			System.out.println("intwebAutomationPrice" + intwebAutomationPrice);

		}
		// To print all course Title of WebAutomation
				List<WebAutomationPojo> webCourses = cd.getCourses().getWebAutomation();
				for (int j = 0; j < webCourses.size(); j++) {
					String webcourseTitle = webCourses.get(j).getCourseTitle();
					System.out.println("webcourseTitle"+webcourseTitle);
				}
		
		// To print all course Title of Api
		int apiCoursesize = cd.getCourses().getApi().size();
		ArrayList<String> apiCourseTitles = new ArrayList<>();
		for (int j = 0; j < apiCoursesize; j++) {
			String apicourseTitle = cd.getCourses().getApi().get(j).getCourseTitle();	
			apiCourseTitles.add(apicourseTitle);
	
		}
		String[] expectedWebCourseTitle= {"Rest Assured Automation using Java","SoapUI Webservices testing"};
		List<String> expctedList = Arrays.asList(expectedWebCourseTitle);
		Assert.assertTrue(apiCourseTitles.equals(expctedList));
	
		
	}

}
