package OAuthSampleExample;

import static io.restassured.RestAssured.*;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import CommonMethods.CommonFunctions;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class OAuthSample {

	@Test
	public void generateAccessTokenAndAccessRSACourse() throws InterruptedException {

		/**Paste this Authroization request url to get the current Url with newly generated code 
		https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
		*This current url needs to be updated every time before execution
		*/
	
		String currenturl ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AeaYSHAt1vwOeW_eGuwLKSRXOd5tAsct-qg7_k-ADQWj5bqeQWKfpDLSpGZUOW5ZNRfnaQ&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		String partCode = currenturl.split("code=")[1];
		String actualCode = partCode.split("&scope")[0];
		System.out.println("actualCode" + actualCode);

		

		// This is to generate Access Token

		String getAccess = given().queryParam("scope", "https://www.googleapis.com/auth/userinfo.email").urlEncodingEnabled(false)
				.queryParam("code", actualCode)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php")
				.queryParam("grant_type", "authorization_code").log().all().when()
				.post("https://www.googleapis.com/oauth2/v4/token").then().log().all().statusCode(200).extract()
				.response().asString();
		JsonPath JsonresponseAccessToken = CommonFunctions.stringRawToJson(getAccess);
	String	accessToken = JsonresponseAccessToken.getString("access_token");
		System.out.println("accessToken"+accessToken);
		
		// This method is to get the course using accessToken

		String res = given().queryParam("access_token", accessToken).log().all().when()
				.get("https://rahulshettyacademy.com/getCourse.php").then().assertThat().statusCode(200).log().all().extract().response().asString();
		System.out.println("Final response"+res);
	}

}
