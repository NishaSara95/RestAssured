package payloads;

public class Payload {

	public static String Payload() {
		return "{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n" + "    \"lng\": 33.427362\r\n"
				+ "  },\r\n" + "  \"accuracy\": 50,\r\n" + "  \"name\": \"Nisha house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n" + "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n" + "  ],\r\n" + "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n" + "}\r\n" + "";
	}

	public static String updatePayload() {
		return "{\r\n" + "\"place_id\":\"5c0d535fab6dec40306275583db0c47e\",\r\n"
				+ "\"address\":\"Cottes more ave UK\",\r\n" + "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "";
	}

	public static String courseDetails() {

		return "{\r\n" + "\r\n" + "\"dashboard\": {\r\n" + "\r\n" + "\"purchaseAmount\": 910,\r\n" + "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n" + "\r\n" + "},\r\n" + "\r\n" + "\"courses\": [\r\n"
				+ "\r\n" + "{\r\n" + "\r\n" + "\"title\": \"Selenium Python\",\r\n" + "\r\n" + "\"price\": 50,\r\n"
				+ "\r\n" + "\"copies\": 6\r\n" + "\r\n" + "},\r\n" + "\r\n" + "{\r\n" + "\r\n"
				+ "\"title\": \"RPA\",\r\n" + "\r\n" + "\"price\": 40,\r\n" + "\r\n" + "\"copies\": 4\r\n" + "\r\n"
				+ "},\r\n" + "\r\n" + "{\r\n" + "\r\n" + "\"title\": \"Cypress\",\r\n" + "\r\n" + "\"price\": 45,\r\n"
				+ "\r\n" + "\"copies\": 10\r\n" + "\r\n" + "}\r\n" + "\r\n" + "]\r\n" + "\r\n" + "}\r\n" + "";
	}

	public static String addBook(String isbn, String aisle) {

		String payload = "{\r\n" + "\r\n" + "\"name\":\"Biography book\",\r\n" + "\"isbn\":\"" + isbn + "\",\r\n"
				+ "\"aisle\":\"" + aisle + "\",\r\n" + "\"author\":\"Nisha\"\r\n" + "}\r\n" + "";
		return payload;
	}

	public static String deleteBook(String id) {
		String payload = "{\r\n" + " \r\n" + "\"ID\" : \"" + id + "\"\r\n" + " \r\n" + "}";
		return payload;

	}

	public static String jiraLogin() {

		String payload = "{\r\n" + "    \"username\": \"nishanthini.s\",\r\n" + "    \"password\": \"Saibaba11!\"\r\n"
				+ "}";
		return payload;

	}

	public static String createIssue() {
		String payload = "{\r\n" + "    \"fields\": {\r\n" + "        \"project\": {\r\n"
				+ "            \"key\": \"STP\"\r\n" + "        },\r\n" + "        \"issuetype\": {\r\n"
				+ "            \"name\": \"Bug\"\r\n" + "        },\r\n" + "        \"summary\": \"Test Defect 3\",\r\n"
				+ "        \"description\": \"This is the sample defect created from Automation third defect\"\r\n"
				+ "    }\r\n" + "}";
		return payload;

	}

	public static String editIssue() {

		String payload = "{\r\n" + "    \"update\": {\r\n" + "    \r\n" + "    },\r\n" + "    \"fields\": {\r\n"
				+ "        \"project\": {\r\n" + "            \"key\": \"STP\"\r\n" + "        },\r\n"
				+ "        \"summary\": \"Test Defect 2\",\r\n"
				+ "        \"description\": \"This is the updated defect created from Postman second defect\"\r\n"
				+ "    }\r\n" + "}";
		return payload;

	}

	public static String addBugComments(String comments) {

		String payload = "{\r\n" + "    \"body\": \""+comments+"\",\r\n" + "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n" + "        \"value\": \"Administrators\"\r\n" + "    }\r\n" + "}"
				+ "}";
		return payload;

	}
}
