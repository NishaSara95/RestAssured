package CommonMethods;

import io.restassured.path.json.JsonPath;

public class CommonFunctions {
	
	
	public static JsonPath stringRawToJson(String response) {
		
		JsonPath js = new JsonPath(response);
		return js;
	}

}
