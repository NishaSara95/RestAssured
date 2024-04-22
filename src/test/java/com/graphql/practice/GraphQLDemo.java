package com.graphql.practice;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import CommonMethods.CommonFunctions;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.equalTo;

public class GraphQLDemo {

	
	@Test 
	public void createLocationEpisodeCharacter() {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String payload = "{\r\n"
				+ "  \"query\": \"mutation  {\\n  \\n  createLocation(location: {name: \\\"Nisha Sara\\\", type: \\\"Soft\\\", dimension: \\\"74\\\"}) {\\n    id\\n  }\\n\\n  createCharacter(character: {name: \\\"Nisha\\\", type: \\\"Good\\\", status: \\\"active\\\", species: \\\"Human\\\", gender: \\\"Female\\\", image: \\\"Good looking\\\", originId: 8465, locationId: 8465}) {\\n    id\\n  }\\n  createEpisode(episode: {name: \\\"Cook with comali\\\", air_date: \\\"12/01/2023\\\", episode: \\\"12\\\"}) {\\n    id\\n  }\\n}\\n\",\r\n"
				+ "  \"variables\": null\r\n"
				+ "}";
		
		String response = given().header("Content-Type","Application/json").body(payload).
		
		
		when().post("/gq/graphql").then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath res = CommonFunctions.stringRawToJson(response);
		String locId = res.getString("data.createLocation.id");
		System.out.println("locId"+locId);
	}
	
}
