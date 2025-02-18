package DataDrivenTesting_Property_File;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class ToLaunchAmazon {

	public static void main(String[] args) throws IOException {
		WebDriver driver;	
		//Object of FIS
		FileInputStream fis=new FileInputStream("./src/test/resources/Test_Data/Amazon.properties"); 
		
		//Object for Properties & load the FIS
		Properties prop=new Properties();
		prop.load(fis);
		
		// Get the data from Property file
		
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String DATA = prop.getProperty("number");
		
		
		// Launch Browser
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}
		else if (BROWSER.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		else {
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(URL);
		driver.findElement(By.id("nav-link-accountList-nav-line-1")).click();
		driver.findElement(By.id("ap_email")).sendKeys(DATA);
		TakesScreenshot ts=(TakesScreenshot)driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshots/Sign_Up.jpeg");
		FileHandler.copy(temp,dest);
		System.out.println("Screenshot is taken......");
		driver.quit();
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
