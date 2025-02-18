package Practise_Java_Concepts;

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

public class ToAutomateFacebookApplication {

	public static void main(String[] args) throws IOException {
		WebDriver driver=null;
		FileInputStream Pfis=new FileInputStream("./src/test/resources/Test_Data/Facebook.properties");
		FileInputStream Afis=new FileInputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		Properties prop=new Properties();
		prop.load(Pfis);
		String URL = prop.getProperty("url");
		String BROWSER = prop.getProperty("browser");
		//String USERNMAE = prop.getProperty("username");
		//String PASSWORD = prop.getProperty("password");
		Workbook wb=WorkbookFactory.create(Afis);
		Sheet sheet = wb.getSheet("Facebook");
		int lastRow = sheet.getLastRowNum();
		
		if (BROWSER.contains("chrome")) {
			driver=new ChromeDriver();
		}else if (BROWSER.contains("firefox")) {
			driver=new FirefoxDriver();
		}else if (BROWSER.contains("edge")) {
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);

		for(int i=1;i<=lastRow;i++) {
			Row row = sheet.getRow(i);
			String Username = row.getCell(1).getStringCellValue();
			String Password = row.getCell(1).getStringCellValue();
			WebElement Usertxt = driver.findElement(By.id("email"));
			Usertxt.clear();
			Usertxt.sendKeys(Username);
			WebElement Passtxt = driver.findElement(By.id("pass"));
			Passtxt.clear();
			Passtxt.sendKeys(Password);
			
			Cell cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("PASS");
			FileOutputStream Afos=new FileOutputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
			wb.write(Afos);	
			
		}
		wb.close();
		driver.quit();
		System.out.println("===EXECUTED===");
	}

}
