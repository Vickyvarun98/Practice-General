package Practise_Java_Concepts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Yatra {

	
	public static void main(String[] args) throws InterruptedException  {
		ChromeOptions setting=new ChromeOptions();
		setting.addArguments("--incognito");
		WebDriver driver = new ChromeDriver(setting);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.yatra.com/");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement element = driver.findElement(By.xpath("//div[@class='css-rd021u']/descendant::div[@class='MuiBox-root css-0']"));
		js.executeScript("arguments[0].click()", element);
		for (;;) {
			try {
				driver.findElement(By.xpath(
						"//span[text()='April 2025']/ancestor::div[@class='react-datepicker__month-container']/descendant::span[text()='06']"))
						.click();
				break;
			} catch (Exception e) {
				
				WebElement element2 = driver.findElement(By.xpath("(//span[@class='react-datepicker__navigation-icon react-datepicker__navigation-icon--next'])[2]"));
				js.executeScript("arguments[0].click()", element2);
			}
		}
		driver.quit();
	}
}
