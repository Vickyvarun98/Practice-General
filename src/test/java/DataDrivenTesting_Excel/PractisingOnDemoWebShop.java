package DataDrivenTesting_Excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PractisingOnDemoWebShop {

	public static void main(String[] args) throws IOException, Exception {
		String Expected_Product1 = "Build your own cheap computer";
		WebDriver driver = null;
		// For Property file
		FileInputStream fis = new FileInputStream("./src/test/resources/Test_Data/DemowebTestData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String URL = prop.getProperty("url");
		String BROWSER = prop.getProperty("browser");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		// For Excel file
		FileInputStream fis1 = new FileInputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Demowebshop");
		String Product1 = sh.getRow(1).getCell(0).getStringCellValue();
		String product2 = sh.getRow(2).getCell(0).getStringCellValue();
		String quantity1 = sh.getRow(1).getCell(1).getStringCellValue();
		String quantity2 = sh.getRow(2).getCell(1).getStringCellValue();

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
		driver.findElement(By.id("small-searchterms")).sendKeys(Product1);
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Thread.sleep(1000);
		WebElement element1 = driver.findElement(By.linkText("Build your own cheap computer"));
		action.scrollToElement(element1).perform();
		element1.click();
		Thread.sleep(2000);
		WebElement Qtybox = driver.findElement(By.id("addtocart_72_EnteredQuantity"));
		action.scrollToElement(Qtybox).click().perform();
		Qtybox.clear();
		Qtybox.sendKeys(quantity1);
		driver.findElement(By.id("add-to-cart-button-72")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Shopping')]")).click();
		Thread.sleep(3000);
		String Actual_Product1 = driver.findElement(By.xpath("//div[@class='page shopping-cart-page']")).getText();
		System.out.println(Actual_Product1);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		if (Actual_Product1.contains(Expected_Product1)) {
			Row row = sh.getRow(1);
			Cell cel = row.createCell(2, CellType.STRING);
			cel.setCellValue("PASS");
			wb.write(fos);
			System.out.println("Product is added to Cart...");
		} else {
			Row row = sh.getRow(1);
			Cell cel = row.createCell(2, CellType.STRING);
			cel.setCellValue("FAIL");
			wb.write(fos);
			System.out.println("Product is not added to Cart...");
		}
		driver.quit();

	}

}
