package TestScriptForContact;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestScriptForCont {

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
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		String Actual_Cell_Value = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String  Actual_Data = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (Actual_Cell_Value.contains(Name)) {
			System.out.println(Name+" contact is added to Database");
		}else {
			System.out.println(Name+" contact is not added to Database");
		}
		System.out.println();
		System.out.println();
		
		if (Actual_Data.equals(Name)) {
			System.out.println("Contact is created");
		}else {
			System.out.println("Contact is not created");
		}
		
		driver.quit();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
