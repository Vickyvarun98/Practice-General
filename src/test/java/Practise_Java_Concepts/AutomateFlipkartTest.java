package Practise_Java_Concepts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;

public class AutomateFlipkartTest {
	
	@Test
	public void automateFlipkart() throws InterruptedException {
		WebDriver driver=null;
		String BROWSER = System.getProperty("browser","CHROME");
		String URL = System.getProperty("url","https://www.flipkart.com/");
		String PRODUCT = System.getProperty("season","iphone 15");
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}else{
			driver=new ChromeDriver();
		}
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.name("q")).sendKeys(PRODUCT,Keys.ENTER);
		String Pro = driver.findElement(By.xpath("//div[contains(text(),'"+PRODUCT+"')]")).getText();
		String Price=null;
		if (Pro.contains(PRODUCT)) {
			Price=driver.findElement(By.xpath("//div[contains(text(),'"+Pro+"')]/ancestor::div[@class='tUxRFH']/descendant::div[text()='₹']/preceding-sibling::div")).getText();
			
			
		} 
		System.out.println(Pro+" -------> "+Price);
	}

}
