package Practise_Java_Concepts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BigBasketTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.get("https://www.bigbasket.com/");
		driver.findElement(By.xpath("//button[@id='headlessui-menu-button-:R5bab6:']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> mainCategories = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='headlessui-menu-items-:R9bab6:']//ul[1]/li/a")));

		for (WebElement mainCategory : mainCategories) {
			String categoryText = mainCategory.getText();
			System.out.println();
			System.out.println("Main Category: " + categoryText);
			System.out.println("================");

			Actions actions = new Actions(driver);
			actions.moveToElement(mainCategory).perform();
			List<WebElement> subcategoryList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='headlessui-menu-items-:R9bab6:']//ul[2]/li/a")));
			
			
			for (WebElement subcategory : subcategoryList) {
				String SubCategory = subcategory.getText();
				System.out.println();
				System.out.println(SubCategory);
				System.out.println("=====================");
				
				actions.moveToElement(subcategory).perform();
				List<WebElement> NewSubcategoryList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='headlessui-menu-items-:R9bab6:']//ul[3]/li/a")));
				
				
				for (WebElement NewSubcategory1 : NewSubcategoryList) {
					String NewSuCatogory = NewSubcategory1.getText();
					System.out.println(NewSuCatogory);
				
				}
				System.out.println("=====================");
			}

		}
		driver.quit();
	}

}  