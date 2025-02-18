package DataDrivenTesting_Excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ToLearnExcelFileOnFacebook {

	public static void main(String[] args) throws IOException {
		WebDriver driver=null;
		//PROPERTY FILE
		FileInputStream fis=new FileInputStream("./src/test/resources/Test_Data/Facebook.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		
		//EXCEL FILE
		FileInputStream FIS=new FileInputStream("./src/test/resources/Test_Data/Test_Case_Data.xlsx");
		Workbook wb=WorkbookFactory.create(FIS);
		Sheet sheet = wb.getSheet("Facebook");
		//For Username
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(1);
		String Value = cell.getStringCellValue();
		
		
		//For Password
		Row row1 = sheet.getRow(1);
		Cell cell1 = row1.getCell(2);
		String Value1 = cell1.getStringCellValue();
		
		System.out.println(Value);
		System.out.println(Value1);
		if(BROWSER.contains("chrome")) {
			driver=new ChromeDriver();
		}
		else if (BROWSER.contains("firefox")) {
			driver=new FirefoxDriver();
		}
		else if (BROWSER.contains("edge")) {
			driver=new EdgeDriver();
		}
		else {
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.id("email")).sendKeys(Value);
		driver.findElement(By.id("pass")).sendKeys(Value1);
		driver.quit();
		
		
		
		
		
		

	}

}
