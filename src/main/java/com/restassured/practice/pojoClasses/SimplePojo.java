package com.restassured.practice.pojoClasses;

public class SimplePojo {

	private String Key1;
	private String Key2;
	
	
	public SimplePojo() {
		
	}
	public SimplePojo(String key1, String key2) {
	
		this.Key1 = key1;
		this.Key2 = key2;
	}
	public String getKey1() {
		return Key1;
	}
	public void setKey1(String key1) {
		Key1 = key1;
	}
	public String getKey2() {
		return Key2;
	}
	public void setKey2(String key2) {
		Key2 = key2;
	}
	
}
