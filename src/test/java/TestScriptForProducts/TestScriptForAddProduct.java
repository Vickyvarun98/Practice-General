package TestScriptForProducts;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestScriptForAddProduct {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver = null;
		// Data from Property file
		FileInputStream fis = new FileInputStream("./src/test/resources/Test_Data/VTiger.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String URL = prop.getProperty("url");
		String BROWSER = prop.getProperty("browser");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		// Data from Excel file
		FileInputStream fis1 = new FileInputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sheet = wb.getSheet("Products");
		int rows = sheet.getPhysicalNumberOfRows();
		// For Browser
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		Row row = sheet.getRow(3);

		String Exp_Prod_Name = row.getCell(2).getStringCellValue();
		String Exp_Part_Num = row.getCell(3).getStringCellValue();
		String Exp_Manufacturer = row.getCell(4).getStringCellValue();
		String Exp_Category = row.getCell(5).getStringCellValue();
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.name("productname")).sendKeys(Exp_Prod_Name);
		// Dropdown for category
		Select cat = new Select(driver.findElement(By.name("productcategory")));
		cat.selectByVisibleText(Exp_Category);
		driver.findElement(By.id("productcode")).sendKeys(Exp_Part_Num);
		// Dropdown for Manufacturer
		Select manu = new Select(driver.findElement(By.name("manufacturer")));
		manu.selectByVisibleText(Exp_Manufacturer);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// Compare & Verify the Products
		String Actual_Prod_Name = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		String Actual_Pro = driver.findElement(By.id("dtlview_Product Name")).getText();
		String Actual_Part_num = driver.findElement(By.id("dtlview_Part Number")).getText();
		String Actual_Category = driver.findElement(By.id("dtlview_Product Category")).getText();
		String Actual_Manufacturer = driver.findElement(By.id("dtlview_Manufacturer")).getText();

		if (Actual_Prod_Name.contains(Exp_Prod_Name)) {
			System.out.println(Exp_Prod_Name + " is added to Products........");
			Cell cell = row.createCell(6, CellType.STRING);
			cell.setCellValue("PASS");
		} else {
			System.out.println(Exp_Prod_Name + " is not added to Products........");
			Cell cell = row.createCell(6, CellType.STRING);
			cell.setCellValue("FAIL");
		}

		if (Actual_Pro.equals(Exp_Prod_Name)) {
			System.out.println(Exp_Prod_Name + " is added........");
		} else {
			System.out.println(Exp_Prod_Name + " is not added........");
		}

		if (Actual_Part_num.equals(Exp_Part_Num)) {
			System.out.println(Exp_Part_Num + " is added........");
		} else {
			System.out.println(Exp_Part_Num + " is not added........");
		}

		if (Actual_Category.equals(Exp_Category)) {
			System.out.println(Exp_Category + " is added........");
		} else {
			System.out.println(Exp_Category + " is not added........");
		}

		if (Actual_Manufacturer.equals(Exp_Manufacturer)) {
			System.out.println(Exp_Manufacturer + " is added........");
		} else {
			System.out.println(Exp_Manufacturer + " is not added........");
		}
		System.out.println();
		System.out.println();

		FileOutputStream fos = new FileOutputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		wb.write(fos);

		wb.close();

		driver.quit();

	}

}
