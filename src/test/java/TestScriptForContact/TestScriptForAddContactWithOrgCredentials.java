package TestScriptForContact;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestScriptForAddContactWithOrgCredentials {

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
		// Random
		Random r = new Random();
		int Ran = r.nextInt(10000);
		// Data from Excel file
		FileInputStream fis1 = new FileInputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		// For Organisation
		Sheet sheet = wb.getSheet("ORG");
		Row row = sheet.getRow(1);
		String Org_Name = row.getCell(2).getStringCellValue() + Ran;
		String PHNO = row.getCell(3).getStringCellValue() + Ran;
		String Industry = row.getCell(5).getStringCellValue();
		String Type = row.getCell(6).getStringCellValue();
		// For Contact
		Sheet sheet1 = wb.getSheet("Contact");
		Row row1 = sheet1.getRow(1);
		String Mob_No = row1.getCell(2).getStringCellValue() + r.nextInt(100);
		String Last_Name = row.getCell(3).getStringCellValue() + Ran;
		Cell cell = row.createCell(4,CellType.STRING);

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
		// For Current_Date
		Date d = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String Start_Date = sim.format(d);

		// For End_Date
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String End_Date = sim.format(cal.getTime());

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		// For Organization
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(Org_Name);
		driver.findElement(By.id("phone")).sendKeys(PHNO);
		Select sel1 = new Select(driver.findElement(By.name("industry")));
		sel1.selectByValue(Industry);
		Select sel2 = new Select(driver.findElement(By.name("accounttype")));
		sel2.selectByVisibleText(Type);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		// For Contacts
		Thread.sleep(3000);
//		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.linkText("Contacts"))));
		try {
			driver.findElement(By.linkText("Contacts")).click();
		} catch (Exception e) {
			driver.findElement(By.linkText("Contacts")).click();
		}
		
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(Last_Name);
		driver.findElement(By.id("mobile")).sendKeys(Mob_No);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@src='themes/softed/images/select.gif']"))
				.click();
		String Parent = driver.getWindowHandle();
		Set<String> Child = driver.getWindowHandles();
		Child.remove(Parent);
		for (String ele : Child) {
			driver.switchTo().window(ele);
			String currentUrl = driver.getCurrentUrl();
			if (currentUrl.contains("Accounts&action")) {
				break;
			}
		}
		driver.findElement(By.name("search_text")).sendKeys(Org_Name);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText(Org_Name)).click();
		driver.switchTo().window(Parent);
		WebElement St_Date = driver.findElement(By.name("support_start_date"));
		St_Date.clear();
		St_Date.sendKeys(Start_Date);
		WebElement Last_Date = driver.findElement(By.name("support_end_date"));
		Last_Date.clear();
		Last_Date.sendKeys(End_Date);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		String Actual_Cell_Value = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String Actual_LastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		String Actual_Org_Name = driver.findElement(By.linkText(Org_Name)).getText();
		String Actual_Mob_No = driver.findElement(By.id("dtlview_Mobile")).getText();
		String Actual_Start_Date = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		String Actual_End_Date = driver.findElement(By.id("dtlview_Support End Date")).getText();

		if (Actual_Cell_Value.contains(Last_Name)) {
			System.out.println("Contact is added to Database");
			cell.setCellValue("PASS");
		} else {
			System.out.println("Contact is not added to Database");
			cell.setCellValue("FAIL");
		}
		System.out.println();
		if (Actual_LastName.equals(Last_Name)) {
			System.out.println("Name is created");
		} else {
			System.out.println("Name is not created");
		}
		System.out.println();

		if (Actual_Org_Name.equalsIgnoreCase(Org_Name)) {
			System.out.println("Organisation is Added...");
		} else {
			System.out.println("Organisation is not Added...");
		}
		System.out.println();
		if (Actual_Mob_No.equals(Mob_No)) {
			System.out.println("Mobile Number is Added...");
		} else {
			System.out.println("Mobile Number is not Added...");
		}
		System.out.println();
		if (Actual_Start_Date.equals(Start_Date)) {
			System.out.println("Starting Date is Added...");
		} else {
			System.out.println("Starting Date is not Added...");
		}
		System.out.println();
		if (Actual_End_Date.equals(End_Date)) {
			System.out.println("Ending Date is Added...");
		} else {
			System.out.println("Ending Date is not Added...");
		}
		FileOutputStream fos=new FileOutputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		wb.write(fos);
		wb.close();
		driver.quit();

	}

}
