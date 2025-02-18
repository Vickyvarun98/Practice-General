package Practise_Java_Concepts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AutomateIPLTest {
	
	@Test
	public void getPointsTable() throws InterruptedException {
		WebDriver driver=null;
		String BROWSER = System.getProperty("browser","CHROME");
		String URL = System.getProperty("url","https://www.iplt20.com/");
		String SEASON = System.getProperty("season","2020");
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
		driver.findElement(By.linkText("POINTS TABLE")).click();
		driver.findElement(By.xpath("//div[contains(text(),'2024')]")).click();
		WebElement YEAR = driver.findElement(By.xpath("//div[contains(text(),'"+SEASON+"')]"));
//		Thread.sleep(2000);
//		Actions action=new Actions(driver);
//		wait.until(ExpectedConditions.visibilityOf(YEAR));
//		action.moveToElement(YEAR).click().perform();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", YEAR);
		
		System.out.println("Season table");
//		String text = driver.findElement(By.xpath("//div[@class='ih-pcard-wrap']/descendant::tbody[@id='pointsdata']")).getText();
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='ih-pcard-wrap']/descendant::tbody[@id='pointsdata']/tr/td[2]"));
		for(WebElement element:elements) {
			String text = element.getText();
			System.out.println(text);
		}
			driver.quit();
		
	}

}
