package com.practice.JiraApi;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CommonMethods.CommonFunctions;
import groovy.ui.GroovySocketServer;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.Iterator;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import payloads.Payload;
import io.restassured.config.EncoderConfig;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class CreateIssue {

	@BeforeMethod
	public void beforeMethod() {
		RestAssured.baseURI = "http://localhost:8080";
	}

	String cookieHeader = "";
	String bugId = "";
	String actualcomment="Adding comment for assertion";

	@Test(priority = 1, enabled = false)
	public void loginJira() {

		String response = given().header("Content-Type", "application/json").body(Payload.jiraLogin()).log().all()
				.when().post("/rest/auth/1/session").then().assertThat().statusCode(200).log().all().extract()
				.response().asString();
		JsonPath js = CommonFunctions.stringRawToJson(response);
		String sessionName = js.getString("session.name");
		String sessionValue = js.getString("session.value");
		cookieHeader = sessionName + "=" + sessionValue;

		System.out.println("cookieHeader" + cookieHeader);

	}

	@Test(priority = 2, enabled = false)
	public void createJiraIssue() {

		String response = given().header("Content-Type", "application/json").header("cookie", cookieHeader)
				.body(Payload.createIssue()).log().all().when().post("/rest/api/2/issue").then().assertThat()
				.statusCode(201).log().all().extract().response().asString();
		JsonPath js = CommonFunctions.stringRawToJson(response);
		bugId = js.getString("id");

		System.out.println("bugId" + bugId);

	}

	@Test(priority = 4, enabled = false)
	public void addingBugComments() {
		String response = given().header("Content-Type", "application/json").header("cookie", cookieHeader)
				.body(Payload.addBugComments(actualcomment)).log().all().when().post("/rest/api/2/issue/" + bugId + "/comment")
				.then().assertThat().statusCode(201).log().all().extract().response().asString();
		JsonPath js = CommonFunctions.stringRawToJson(response);
		String commentBody = js.getString("body");
		Assert.assertEquals(commentBody, "Adding comments using Automation");

	}

	@Test
	public void addingCommentsUsingPathAndSessionFilter() {

	
		// Login Jira using Session Filter
		SessionFilter session = new SessionFilter();
		String response = given().header("Content-Type", "application/json").body(Payload.jiraLogin()).log().all()
				.filter(session).when().post("/rest/auth/1/session").then().assertThat().statusCode(200).log().all()
				.extract().response().asString();

		// Add comments
	
		String asString = given().header("Content-Type", "application/json").filter(session).pathParam("key", "STP-2")
				.body(Payload.addBugComments(actualcomment)).log().all().when().post("/rest/api/2/issue/{key}/comment").then()
				.assertThat().statusCode(201).log().all().extract().response().asString();
		JsonPath getcommentsResponse = CommonFunctions.stringRawToJson(asString);
		String commentBody = getcommentsResponse.get("body");
		System.out.println("commentBody" + commentBody);
		String originalcommentId = getcommentsResponse.get("id");
		System.out.println("commentId" + originalcommentId);

//		// To add an attachment to the existing issue
//		given().header("Atlassian-Token","no-check").filter(session).pathParam("key", "STP-2").header("Content-Type","multipart/form-data").
//		multiPart("file", new File("src//test//resources//jira.txt")).log().all().when().post("/rest/api/2/issue/{key}/attachments").then().log().all();

		// Get jira issue

		String string = given().header("Atlassian-Token", "no-check").filter(session).pathParam("key", "STP-2")
				.queryParam("fields", "comment").log().all().when().get("/rest/api/2/issue/{key}").then().assertThat()
				.statusCode(200).log().all().extract().response().asString();
		System.out.println("string response " + string);
		JsonPath getIssueResponse = CommonFunctions.stringRawToJson(string);
		int idCount = getIssueResponse.getInt("fields.comment.comments.id.size()");


		for (int i = 0; i < idCount; i++) {

			String expectedcommentId = getIssueResponse.get("fields.comment.comments["+i+"].id").toString();
			System.out.println("expectedcommentId"+expectedcommentId);
			if (expectedcommentId.equals(originalcommentId)) {
				String expectedcommentBody = getIssueResponse.get("fields.comment.comments["+i+"].body").toString();
				System.out.println("expectedcommentBody"+expectedcommentBody);
				Assert.assertEquals(expectedcommentBody,actualcomment);
				break;
			}
		
			
		}

	}

	@Test(priority = 3, enabled = false)
	public void getJiraIssue() {
		SessionFilter session = new SessionFilter();

		given().header("Content-Type", "application/json").header("cookie", cookieHeader).body(Payload.editIssue())
				.log().all().when().post("/rest/api/2/issue/" + bugId + "/").then().assertThat().statusCode(204).log()
				.all();

		System.out.println("bugId" + bugId);

	}

}
