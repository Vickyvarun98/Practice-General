package DataDrivenTesting_XML;

import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class ToLearnDataDrivenTestingByXML {
	@Test
	public void learnBasicsofXml(XmlTest test) {
		
		
		String URL = test.getParameter("url");
		String BROWSER = test.getParameter("browser");
		String USERNAME = test.getParameter("username");
		String PASSWORD = test.getParameter("password");
		
		System.out.println(URL);
		System.out.println(BROWSER);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
		System.out.println("===EXECUTED===");
	}



}
