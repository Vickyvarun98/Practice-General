package Practise_Java_Concepts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class AutomateICC_T20_Rankings {

//@Test
//public void automateICC() throws InterruptedException {
	public static void main(String[] args) throws InterruptedException {
		ChromeOptions setting=new ChromeOptions();
		setting.addArguments("--incognito");// To avoid notification popup
		WebDriver driver = new ChromeDriver(setting);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.icc-cricket.com/");
		driver.findElement(By.xpath("//a[@data-tag='rankings'and text()='Rankings']")).click();
		driver.findElement(
				By.xpath("//a[@href='/rankings/team-rankings/mens/t20i']/child::span[text()='Full Rankings']")).click();
		//Thread.sleep(2000);
		Actions action = new Actions(driver);
		for (;;) {
			String Table = driver.findElement(By.xpath("//div[@class='si-table-footer']")).getText();
			if (Table.contains("LOAD")) {
				WebElement Load = driver.findElement(By.xpath("//span[text()='Load More']"));
				action.moveToElement(Load).perform();
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click()", Load);
			} else {
				System.out.println("Break done...");
				break;
			}
		}
		List<WebElement> Positions = driver
				.findElements(By.xpath("//div[@class='si-table-body']/descendant::div[@class='si-table-data si-pos']"));
		List<WebElement> Teams = driver.findElements(
				By.xpath("//div[@class='si-table-body']/descendant::div[@class='si-table-data si-team']"));
		List<WebElement> Points = driver
				.findElements(By.xpath("//div[@class='si-table-body']/descendant::div[@class='si-table-data si-pts']"));
		List<WebElement> Ratings = driver.findElements(
				By.xpath("//div[@class='si-table-body']/descendant::div[@class='si-table-data si-rating']"));
		int size = Positions.size();
		for (int i = 0; i < size; i++) {

			System.out.println(Positions.get(i).getText() + "   " + Teams.get(i).getText() + "   "
					+ Points.get(i).getText() + "   " + Ratings.get(i).getText());
		}

		driver.quit();

	}
}
