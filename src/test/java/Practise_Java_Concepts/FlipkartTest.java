package Practise_Java_Concepts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlipkartTest {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.flipkart.com/");
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='1ch8e']"));
		//List<WebElement> elements = driver.findElements(By.xpath("//div[@class='_3sdu8W emupdz']"));
		//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		//wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		Thread.sleep(1000);
		for(WebElement mainCatEl:elements) {
			Actions act=new Actions(driver);
			act.moveToElement(mainCatEl).perform();
			List<WebElement> elements2 = driver.findElements(By.xpath("//div[@class='_16rZTH']/object/a"));
			//wait.until(ExpectedConditions.visibilityOfAllElements(elements2));
			Thread.sleep(1000);
			for(WebElement SubEle:elements2) {
				String SubCato = SubEle.getText();
				System.out.println(SubCato);
				System.out.println("=======================");
				act.moveToElement(SubEle).perform();
				List<WebElement> elements3 = driver.findElements(By.xpath("//div[@class='31z7R']/object/a"));
				//wait.until(ExpectedConditions.visibilityOfAllElements(elements3));
				Thread.sleep(1000);
				for(WebElement NewSubEl:elements3) {
					String NewSubCAt = NewSubEl.getText();
					System.out.println(NewSubCAt);
					
				}
				System.out.println("========================");
				
			}
		}
	}

}
