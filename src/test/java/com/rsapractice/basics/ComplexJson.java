package com.rsapractice.basics;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import payloads.Payload;

public class ComplexJson {

	@Test
	public void complexJson() {

		JsonPath json = new JsonPath(Payload.courseDetails());

		// Print No of courses returned by API
		List<Integer> Coursenum = json.getList("courses");
		int NumberOfCourses = Coursenum.size();
		System.out.println("NumberOfCourses " + NumberOfCourses);

		// Print the purchase amount
		int purchaseAmt = json.getInt("dashboard.purchaseAmount");
		System.out.println("PurchaseAmount " + purchaseAmt);

		// Print Title of the first course
		String firstCourseTitle = json.getString("courses[0].title");
		System.out.println("firstCourseTitle " + firstCourseTitle);

		// Print All course titles and their respective Prices
		List<String> allCourseTitle = json.getList("courses.title");
		List<Integer> allCoursePrice = json.getList("courses.price");

		// Iterate through the lists and print each title with its corresponding price
		for (int i = 0; i < allCourseTitle.size(); i++) {
			String title = allCourseTitle.get(i);
			Integer price = allCoursePrice.get(i);
			System.out.println("Title: " + title + ", Price: " + price);
		}

		// Print no of copies sold by RPA Course

		List<Integer> numberOfCopies = json.getList("courses.copies");
		List<String> TitleCourse = json.getList("courses.title");
		int rpaCopiesSold = 0;
		for (int i = 0; i < TitleCourse.size(); i++) {
			if (TitleCourse.get(i).equals("RPA")) {
				rpaCopiesSold = numberOfCopies.get(i);

				break;
			}
		}
		System.out.println("rpaCopiesSold " + rpaCopiesSold);


		// Verify if Sum of all Course prices matches with Purchase Amount

		List<Integer> courseprice = json.getList("courses.price");
		List<Integer> courseCopies = json.getList("courses.copies");
		int sumOfAllCourse = 0;
		for (int i = 0; i < courseprice.size(); i++) {
			sumOfAllCourse += courseprice.get(i) * courseCopies.get(i);

		}
		//Assert.assertEquals(sumOfAllCourse, purchaseAmt);
		System.out.println("sumOfAllCourse " + sumOfAllCourse +  " Purchase Amount " + purchaseAmt);

	}

}
