package DataDrivenTesting_Property_File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.poi.EncryptedDocumentException;
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

public class ToAddProductToCartOnDemowebshop {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, Exception {

		WebDriver driver = null;

		FileInputStream fis = new FileInputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		FileInputStream fis1 = new FileInputStream("./src/test/resources/Test_Data/DemowebTestData.properties");
		Properties prop = new Properties();
		prop.load(fis1);
		String URL = prop.getProperty("url");
		String BROWSER = prop.getProperty("browser");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Demowebshop");
		int lastrow = sh.getLastRowNum();
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
		

		Actions action = new Actions(driver);
		for (int i = 1; i <= lastrow; i++) {
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.id("Email")).sendKeys(USERNAME);
			driver.findElement(By.id("Password")).sendKeys(PASSWORD);
			driver.findElement(By.xpath("//input[@value='Log in']")).click();
			String Expected_Product_1 = "Build your own expensive computer";
			String Expected_Product_2 = "Health Book";
			String Expected_Product_3 = "Blue Jeans";
			Row row = sh.getRow(i);
			String Product = row.getCell(0).getStringCellValue();
			String Quantity = row.getCell(1).getStringCellValue();
			WebElement Searchbox = driver.findElement(By.id("small-searchterms"));
			Searchbox.sendKeys(Product);
			WebElement LoginButton = driver.findElement(By.xpath("//input[@value='Search']"));
			LoginButton.click();
			String Pro = driver.findElement(By.xpath("//div[@class='product-grid']")).getText();
			WebElement Pro_Qty = null;
			if (Pro.contains(Expected_Product_1)) {
				driver.findElement(By.linkText(Expected_Product_1)).click();
				Pro_Qty = driver.findElement(By.id("addtocart_74_EnteredQuantity"));

			} else if (Pro.contains(Expected_Product_2)) {
				driver.findElement(By.linkText(Expected_Product_2)).click();
				Pro_Qty = driver.findElement(By.id("addtocart_22_EnteredQuantity"));

			} else if (Pro.contains(Expected_Product_3)) {
				driver.findElement(By.linkText(Expected_Product_3)).click();
				Pro_Qty = driver.findElement(By.id("addtocart_36_EnteredQuantity"));

			}
			action.moveToElement(Pro_Qty).perform();
			Pro_Qty.clear();
			Pro_Qty.sendKeys(Quantity);
			

			WebElement Web_Page = driver.findElement(By.xpath("//div[@class='overview']"));
			String text = "";
			text = Web_Page.getText();

			if (text.contains(Expected_Product_1)) {
				driver.findElement(By.id("add-to-cart-button-74")).click();

			} else if (text.contains(Expected_Product_2)) {
				driver.findElement(By.id("add-to-cart-button-22")).click();

			} else if (text.contains(Expected_Product_3)) {
				driver.findElement(By.id("add-to-cart-button-36")).click();//

			}
			WebElement Shopping_Cart = driver.findElement(By.xpath("//span[contains(text(),'Shopping')]"));
			Shopping_Cart.click();
			Thread.sleep(2000);
			WebElement Cart_Web_Page = driver.findElement(By.xpath("//tr[@class='cart-item-row']"));
			String text2 = Cart_Web_Page.getText();
			if (text2.contains(Expected_Product_1)) {
				Cell cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("PASS");
				System.out.println(Expected_Product_1 + " is added to cart...");
			} else if (text2.contains(Expected_Product_2)) {
				Cell cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("PASS");
				System.out.println(Expected_Product_2 + " is added to cart...");
			} else if (text2.contains(Expected_Product_3)) {
				Cell cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("PASS");
				System.out.println(Expected_Product_3 + " is added to cart...");
			} else {
				Cell cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("FAIL");
				System.out.println("Product is not added to Cart....");
			}
			FileOutputStream fos=new FileOutputStream("./src/test/resources/Test_Data/DemowebTestData.xlsx");
			wb.write(fos);
			driver.findElement(By.xpath("//img[@alt='Tricentis Demo Web Shop']")).click();
			driver.findElement(By.linkText("Log out")).click();
			Thread.sleep(2000);

		}
		wb.close();
		driver.quit();
	}

}
