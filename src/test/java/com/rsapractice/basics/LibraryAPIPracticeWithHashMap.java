package com.rsapractice.basics;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import CommonMethods.CommonFunctions;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

import payloads.Payload;

public class LibraryAPIPracticeWithHashMap {

	@Test(dataProvider = "BookData")
	public void addBook(String isbn, String aisle) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Biography book");
		map.put("isbn", isbn);
		map.put("aisle", aisle);
		map.put("author", "Nisha");


		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type", "application/json")
				.body(map).log()
				.all().when().post("/Library/Addbook.php").then().assertThat().statusCode(200)
				.body("Msg", equalTo("successfully added")).log().all().extract().response().asString();

		JsonPath js = CommonFunctions.stringRawToJson(response);
		String id = js.get("ID");

		given().header("Content-Type", "application/json").body(Payload.deleteBook(id)).log().all().when()
				.post("/Library/DeleteBook.php").then().assertThat().statusCode(200)
				.body("msg", equalTo("book is successfully deleted")).log().all().extract().response().asString();

		System.out.println("Book added and then deleted successfully");

	}

	@DataProvider(name = "BookData")
	public Object getData() {

		return new Object[][] { { "13467545673", "14335" }, { "134674333345", "6337" } };
	}

}
