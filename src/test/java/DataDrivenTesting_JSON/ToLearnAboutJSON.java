package DataDrivenTesting_JSON;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

public class ToLearnAboutJSON {
	@Test
	public void toLearnJSON() throws FileNotFoundException, IOException, ParseException { 
		/*
		 if any method is static it won't participate in cross browser execution & while taking screenshot
		 */
		// Get Physical path from JSON file
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("./src/test/resources/Test_Data/VTiger.json"));

		// Typecast Java Object to JSON Object
		JSONObject json = (JSONObject) obj;

		// Get data from JSON object
		String URL = json.get("url").toString();
		String BROWSER = json.get("browser").toString();
		String USERNAME = json.get("username").toString();
		String PASSWORD = json.get("password").toString();

		Object URL1 = json.get("url");
		Object BROWSER1 = json.get("browser");
		Object USERNAME1 = json.get("username");
		Object PASSWORD1 = json.get("password");
		Object TIMEOUT1 = json.get("timeout");

		System.out.println(URL);
		System.out.println(BROWSER);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
		System.out.println();
		System.out.println(URL1);
		System.out.println(BROWSER1);
		System.out.println(USERNAME1);
		System.out.println(PASSWORD1);
		System.out.println(TIMEOUT1);
	

	}

}
