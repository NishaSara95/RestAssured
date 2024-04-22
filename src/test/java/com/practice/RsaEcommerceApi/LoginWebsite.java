package com.practice.RsaEcommerceApi;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.restassured.practice.pojoClasses.LoginPojo;
import com.restassured.practice.pojoClasses.LoginResponsePojo;
import com.restassured.practice.pojoClasses.OrderDetailPojo;
import com.restassured.practice.pojoClasses.OrderPojo;
import com.restassured.practice.pojoClasses.OrdersResponsePojo;

import CommonMethods.CommonFunctions;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class LoginWebsite {

	String token;
	String userId;

	@Test(priority = 1)
	public void loginEcommerce() {

		RequestSpecification request = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType("Application/json").log(LogDetail.HEADERS).build();
		ResponseSpecification response = new ResponseSpecBuilder().log(LogDetail.ALL).expectContentType("Application/json")
				.expectStatusCode(200).build();

		LoginPojo login = new LoginPojo();
		login.setUserEmail("nishars1995@gmail.com");
		login.setUserPassword("Hello123");

		RequestSpecification req = given().spec(request).body(login);

		LoginResponsePojo Stringresponse = req.when().post("/api/ecom/auth/login").then().spec(response).extract()
				.response().as(LoginResponsePojo.class);

		userId = Stringresponse.getUserId();
		token = Stringresponse.getToken();
		String message = Stringresponse.getMessage();

		System.out.println("token" + token);
		System.out.println("userId" + userId);
		System.out.println("message" + message);

		// Add the product

		RequestSpecification addProductbaserequest = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token)
		        .build();

		RequestSpecification addProductRequest = given().log().all().spec(addProductbaserequest).param("productName","Zara Shirt").
		param("productAddedBy", userId).
		param("productCategory", "fashion").
		param("productSubCategory", "shirts").
		param("productPrice", "11500").
		param("productDescription", "Zara Originals").
		param("productFor", "women").
		multiPart("productImage",new File("C:\\Users\\Windows 11\\Downloads\\download (1).jpg"));
		
		String respString = addProductRequest.when().post("/api/ecom/product/add-product").then().assertThat().statusCode(201).extract().response()
				.asString();

		JsonPath stringRawToJson = CommonFunctions.stringRawToJson(respString);
		String prodId = stringRawToJson.get("productId");
		String Message = stringRawToJson.get("message");
		System.out.println("prodId" + prodId);
		System.out.println("Message" + Message);
		
		// Place an order
		
		RequestSpecification placeProductOrderbaserequest = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).setContentType(ContentType.JSON)
		        .build();
		
		
		OrderDetailPojo orderDetail=  new OrderDetailPojo();
		OrderPojo OrderPojo = new OrderPojo();
;		
		List<OrderDetailPojo> orderList = new ArrayList<OrderDetailPojo>();
		orderList.add(orderDetail);
		
		orderDetail.setCountry("India");
		orderDetail.setProductOrderedId(prodId);
		OrderPojo.setOrders(orderList);
		
		
		RequestSpecification placeProductOrderrequest = given().log().all().spec(placeProductOrderbaserequest).body(OrderPojo);

		String addordersResponse = placeProductOrderrequest.when().post("api/ecom/order/create-order").then().extract().response().asString();
			System.out.println("ordersResponsePojo"+addordersResponse);	
			
			JsonPath stringRawToJson2 = CommonFunctions.stringRawToJson(addordersResponse);
			String productOrderId = stringRawToJson2.getString("productOrderId");
			
			// Delete the product
			
			RequestSpecification deleteProdBaseReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
					.addHeader("Authorization", token)
			        .build();
			
			RequestSpecification deleteProdReq = given().spec(deleteProdBaseReq).pathParam("productOrderedId", prodId);
			
			
			String delResponse = deleteProdReq.when().delete("api/ecom/product/delete-product/{productOrderedId}").then().log().all().extract().response().asString();
			System.out.println("delResponse"+delResponse);

	}

}