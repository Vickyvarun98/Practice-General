package DataDrivenTestUsingCmd;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ToPerformTaskOnDemowebshop {

	@Test
	public void demowebShop() throws EncryptedDocumentException, IOException {

		String BROWSER = System.getProperty("browser");
		String URL = System.getProperty("url");
		String USERNAME = System.getProperty("username");
		String PASSWORD = System.getProperty("password");

		WebDriver driver = null;

		FileInputStream fis = new FileInputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Demowebshop");
		Row row = sh.getRow(2);
		String Product = row.getCell(0).getStringCellValue();
		String Quantity = row.getCell(1).getStringCellValue();
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(URL);
		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.id("Email")).sendKeys(USERNAME);
		driver.findElement(By.id("Password")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		driver.findElement(By.id("small-searchterms")).sendKeys(Product);
		driver.findElement(By.xpath("//input[@value='Search']")).click();
		driver.findElement(By.partialLinkText("Health")).click();
		List<WebElement> elements = driver.findElements(By.xpath("//div[@itemprop='description']"));
		for (WebElement element : elements) {
			String text = element.getText();
			System.out.println(text);
		}
		WebElement Qty = driver.findElement(By.id("addtocart_22_EnteredQuantity"));
		Qty.clear();
		Qty.sendKeys(Quantity);
		driver.findElement(By.id("add-to-cart-button-22")).click();
		System.out.println("Product is added to cart......");

		driver.findElement(By.linkText("Log out")).click();
		System.out.println("Successfully Logged Out...");
		driver.quit();

	}
}
