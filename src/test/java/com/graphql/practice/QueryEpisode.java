package com.graphql.practice;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import CommonMethods.CommonFunctions;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class QueryEpisode {
	
	
	@Test 
	public void QueryEpisodeExample() {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String payload = "{\r\n"
				+ "    \"query\": \"\\n\\nquery($characterId:Int!,$episodeId:Int! ){\\n  character(characterId:$characterId){\\n    id\\n    name\\n    type\\n  }\\n  \\n  episode(episodeId:$episodeId){\\n    id\\n    name\\n    air_date\\n    episode\\n  }\\n}\\n\\n\",\r\n"
				+ "    \"variables\": {\r\n"
				+ "        \"characterId\": 7317,\r\n"
				+ "        \"episodeId\": 5844\r\n"
				+ "    }\r\n"
				+ "}";
		
		String response = given().header("Content-Type","Application/json").body(payload).
		
		
		when().post("/gq/graphql").then().log().all().statusCode(200).extract().response().asString();
		
		JsonPath res = CommonFunctions.stringRawToJson(response);
		String actualName = res.getString("data.episode.name");
		Assert.assertEquals(actualName, "Cook with comali");
	}
	

}
