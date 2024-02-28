package com.restassured.practice.requestheaders;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.hamcrest.core.IsInstanceOf;
import org.testng.Assert;
import org.testng.annotations.Test;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.restassured.config.LogConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class HamcrestExample {

	// Static Imports : import static org.hamcrest.MatcherAssert.assertThat;
	// Static Imports: import static io.restassured.RestAssured.*;
	// Static Imports: import static org.hamcrest.Matchers.*;
	// Static Imports: import static org.hamcrest.Matchers.*;

	// Given >> Pre requestion
	// When>> set of actions
	// Then >> Assertions

	@Test
	public void validateUsingHamcrest() {

		String name = given().baseUri("https://api.postman.com")
				.header("x-api-key", "PMAK-659d95664380a40bdd208044-da49c168ef3b7b6f9382a2e5c32f0d2eb2").when()
				.get("/workspaces").then().assertThat().statusCode(200).extract().response().path("workspaces.name[0]");
		System.out.println("Workspace name is " + name);
		// Hamcrest assertion
		assertThat(name, equalTo("My Workspaces"));
		// TestNg assertion
		Assert.assertEquals(name, "My Workspace");

	}

	@Test
	public void validateResponse() {

		given().baseUri("https://api.postman.com")
				.header("x-api-key", "PMAK-659d95664380a40bdd208044-da49c168ef3b7b6f9382a2e5c32f0d2eb2").when()
				.get("/workspaces").then().assertThat().statusCode(200);
		System.out.println("Status validated");

	}
	
	/** This method is used to validate the response body Using Ham crest Methods 
	 * such as contains,containsInAnyOrder,hasItem,is(not(empty()),hasSize, hasKey,hasEntry
	 * 
	 */
	@Test

	public void validateResponseBodyUsingHamcrest() {

		given().baseUri("https://api.postman.com")
		.header("x-api-key", "PMAK-659d95664380a40bdd208044-da49c168ef3b7b6f9382a2e5c32f0d2eb2").
		log().all().
		when()
		.get("/workspaces")
		.then().log().ifError().assertThat().statusCode(200).
		body("workspaces.name", containsInAnyOrder("Workspace 3","Workspace2","My Workspace")
		,"workspaces.name", is(not(empty()))
		,"workspaces.name", hasSize(3)
		,"workspaces[0]",hasKey("id")
		,"workspaces[0]",hasValue("personal")
		,"workspaces[0]",hasEntry("type", "personal")
		,"workspaces[0]", not(equals(Collections.EMPTY_MAP)));
		
		
		
	}
	
	/** This method is to learn how to blacklist headers in log 
	 * 
	 */
	@Test
	public void blackListHeadersLearning() {
		Set<String> headers= new LinkedHashSet<String>();
		headers.add("x-api-key");
		headers.add("Cookies");
		
		given().baseUri("https://api.postman.com")
		.header("x-api-key", "PMAK-659d95664380a40bdd208044-da49c168ef3b7b6f9382a2e5c32f0d2eb2").
		config(config.logConfig(LogConfig.logConfig().blacklistHeaders(headers))).
		log().all().
		
		when()
		.get("/workspaces")
		.then().log().ifError().assertThat().statusCode(200).
		body("workspaces.name", containsInAnyOrder("Workspace 3","Workspace2","My Workspace")
		,"workspaces.name", is(not(empty()))
		,"workspaces.name", hasSize(3)
		,"workspaces[0]",hasKey("id")
		,"workspaces[0]",hasValue("personal")
		,"workspaces[0]",hasEntry("type", "personal")
		,"workspaces[0]", not(equals(Collections.EMPTY_MAP)));
		
		
		
	}
}
