package DataDrivenTesting_Property_File;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ToLearnDDTOnVTiger {

	public static void main(String[] args) throws IOException {

		WebDriver driver = null ;
//		FileInputStream fis = new FileInputStream("C:\\Users\\vino\\OneDrive\\Desktop\\VTiger.properties");
		FileInputStream fis = new FileInputStream("./src/test/resources/Test_Data/VTiger.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		String ORGANISATION = prop.getProperty("organisation");
		String WEBSITE = prop.getProperty("website");
		String EMPLOYEE = prop.getProperty("employee");
		String MAIL = prop.getProperty("mail");
		String ADDRESS = prop.getProperty("address");
		String PO = prop.getProperty("po");
		String CITY = prop.getProperty("city");
		String STATE = prop.getProperty("state");
		String CODE = prop.getProperty("code");
		String COUNTRY = prop.getProperty("country");
		String PHONE = prop.getProperty("phone");
		String FAX = prop.getProperty("fax");
		String MBILE = prop.getProperty("mobile");
		String EMAIL = prop.getProperty("email");
		String OWNER = prop.getProperty("owner");
		String SIC = prop.getProperty("sic");
		String REVENUE = prop.getProperty("revenue");
		String DESCRIPTION = prop.getProperty("description");

		if (BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.contains("egde")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(ORGANISATION);
		driver.findElement(By.xpath("//input[@size='27']")).sendKeys(WEBSITE);
		driver.findElement(By.id("employees")).sendKeys(EMPLOYEE);
		driver.findElement(By.id("email2")).sendKeys(MAIL);
		driver.findElement(By.name("emailoptout")).click();
		driver.findElement(By.xpath("//input[@value='U']")).click();
		driver.quit();
	}

}
