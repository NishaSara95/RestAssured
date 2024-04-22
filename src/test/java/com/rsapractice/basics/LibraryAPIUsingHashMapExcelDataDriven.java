package com.rsapractice.basics;


import org.testng.annotations.Test;

import CommonMethods.CommonFunctions;
import DataDriveTesting.ExcelDataDriven;

import static org.hamcrest.Matchers.equalTo;


import java.util.ArrayList;
import java.util.HashMap;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

import payloads.Payload;

public class LibraryAPIUsingHashMapExcelDataDriven {

	@Test
	public void addBookDeleteBook() throws Exception {

		ExcelDataDriven data = new ExcelDataDriven();
		ArrayList<Object> addBook = data.getData("LibraryAddBook");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", addBook.get(0));
		map.put("isbn", addBook.get(1));
		map.put("aisle", addBook.get(2));
		map.put("author", addBook.get(3));

		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type", "application/json").body(map).log().all().when()
				.post("/Library/Addbook.php").then().assertThat().statusCode(200)
				.body("Msg", equalTo("successfully added")).log().all().extract().response().asString();

		JsonPath js = CommonFunctions.stringRawToJson(response);
		String id = js.get("ID");

		given().header("Content-Type", "application/json").body(Payload.deleteBook(id)).log().all().when()
				.post("/Library/DeleteBook.php").then().assertThat().statusCode(200)
				.body("msg", equalTo("book is successfully deleted")).log().all().extract().response().asString();

		System.out.println("Book added and then deleted successfully");

	}

}
