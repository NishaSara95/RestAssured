package com.restassured.practice.pojoClasses;

import java.util.List;

public class CourseDetailsPojo {

	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private CoursesPojo courses;
	private String linkedIn;
	
	



	public CoursesPojo getCourses() {
		return courses;
	}

	public void setCourses(CoursesPojo courses) {
		this.courses = courses;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}



	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

}
