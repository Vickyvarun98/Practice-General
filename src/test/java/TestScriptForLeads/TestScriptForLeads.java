package TestScriptForLeads;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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

public class TestScriptForLeads {

	public static void main(String[] args) throws IOException {
		WebDriver driver = null;
		// Random Number
		Random r = new Random();
		int ran = r.nextInt(10000);
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
		Sheet sheet = wb.getSheet("Leads");
		Row row = sheet.getRow(3);
		String Prefix = row.getCell(2).getStringCellValue();
		String Exp_Fir_Name = row.getCell(3).getStringCellValue();
		String Exp_Lst_Name = row.getCell(4).getStringCellValue();
		String Exp_Company = row.getCell(5).getStringCellValue();
		String Exp_PHNO = row.getCell(6).getStringCellValue() + ran;

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
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		Select pre = new Select(driver.findElement(By.name("salutationtype")));
		pre.selectByVisibleText(Prefix);
		driver.findElement(By.name("firstname")).sendKeys(Exp_Fir_Name);
		driver.findElement(By.name("lastname")).sendKeys(Exp_Lst_Name);
		driver.findElement(By.name("company")).sendKeys(Exp_Company);
		driver.findElement(By.id("mobile")).sendKeys(Exp_PHNO);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// Compare & Verify the Products
		String text = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String Actual_Prefix = driver.findElement(By.id("mouseArea_First Name")).getText();
		String Actual_Fir_Name = driver.findElement(By.id("dtlview_First Name")).getText();
		String Actual_Last_Name = driver.findElement(By.id("dtlview_Last Name")).getText();
		String Actual_Company = driver.findElement(By.id("dtlview_Company")).getText();
		String Actual_PHNO = driver.findElement(By.id("dtlview_Mobile")).getText();

		if (text.contains(Exp_Lst_Name)) {
			System.out.println("Name is added to Leads........");
			Cell cell = row.createCell(7, CellType.STRING);
			cell.setCellValue("PASS");
		} else {
			System.out.println("Name is not added to Leads........");
			Cell cell = row.createCell(7, CellType.STRING);
			cell.setCellValue("FAIL");
		}

		System.out.println();
//		if (Actual_Prefix.equals(Prefix)) {
//			System.out.println("Prefix is added........");
//		} else {
//			System.out.println("Prefix is not added........");
//		}

		if (Actual_Fir_Name.equals(Exp_Fir_Name)) {
			System.out.println(Exp_Fir_Name + " is added........");
		} else {
			System.out.println(Exp_Fir_Name + " is not added........");
		}
		System.out.println();
		if (Actual_Last_Name.equals(Exp_Lst_Name)) {
			System.out.println(Exp_Lst_Name + " is added........");
		} else {
			System.out.println(Exp_Lst_Name + " is not added........");
		}
		System.out.println();
		if (Actual_Company.equals(Exp_Company)) {
			System.out.println(Exp_Company + " is added........");
		} else {
			System.out.println(Exp_Company + " is not added........");
		}
		System.out.println();
		if (Actual_PHNO.equals(Exp_PHNO)) {
			System.out.println(Exp_PHNO + " is added........");
		} else {
			System.out.println(Exp_PHNO + " is not added........");
		}

		FileOutputStream fos = new FileOutputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		wb.write(fos);
		wb.close();

		driver.quit();

	}

}
