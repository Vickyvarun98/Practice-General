package TestScriptForContact;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

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

public class TestScriptForAddDateOnContact {

	public static void main(String[] args) throws IOException {
		WebDriver driver=null;
		//Data from Property file
		FileInputStream fis =new FileInputStream("./src/test/resources/Test_Data/VTiger.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String URL = prop.getProperty("url");
		String BROWSER = prop.getProperty("browser");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		//Random
		Random r=new Random();
		int Ran = r.nextInt(10000);
		//Data from Excel file
		FileInputStream fis1=new FileInputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Sheet sheet = wb.getSheet("Contact");
		Row row = sheet.getRow(1);
		String cell_Value = row.getCell(2).getStringCellValue()+r.nextInt(100);
		
		String Name = row.getCell(3).getStringCellValue()+ Ran ;
		
		//For Browser
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else if (BROWSER.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		} else{
			driver=new ChromeDriver();
		}
		//For Current_Date
		Date d=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String Current_Date = sim.format(d);
		
		//For End_Date
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);
		String Last_Date = sim.format(cal.getTime());
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(Name);
		driver.findElement(By.id("mobile")).sendKeys(cell_Value);
		WebElement Start_Date = driver.findElement(By.name("support_start_date"));
		Start_Date.clear();
		Start_Date.sendKeys(Current_Date);
		WebElement End_Date = driver.findElement(By.name("support_end_date"));
		End_Date.clear();
		End_Date.sendKeys(Last_Date);
		
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		String Actual_Cell_Value = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String  Actual_Data = driver.findElement(By.id("dtlview_Last Name")).getText();
		String Actual_Start_Date = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		String Actual_End_Date = driver.findElement(By.id("dtlview_Support End Date")).getText();
		
		
	
		if (Actual_Cell_Value.contains(Name)) {
			System.out.println(Name+" contact is added to Database");
		}else {
			System.out.println(Name+" contact is not added to Database");
		}
		System.out.println();
	
		
		if (Actual_Data.equals(Name)) {
			System.out.println("Contact is created");
		}else {
			System.out.println("Contact is not created");
		}
		System.out.println();
		if (Actual_Start_Date.equals(Current_Date)) {
			System.out.println(Actual_Start_Date+" Date is added ");
		}else {
			System.out.println(Actual_Start_Date+" date is not added");
		}
		System.out.println();
		if (Actual_End_Date.equals(Last_Date)) {
			System.out.println(Actual_End_Date+" Date is added ");
		}else {
			System.out.println(Actual_End_Date+" date is not added");
		}
		driver.quit();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
