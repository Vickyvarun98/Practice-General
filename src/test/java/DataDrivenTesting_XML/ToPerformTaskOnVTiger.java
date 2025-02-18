package DataDrivenTesting_XML;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
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
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class ToPerformTaskOnVTiger {
	@Test
	public void createOrg(XmlTest test) throws InterruptedException, EncryptedDocumentException, IOException {
		WebDriver driver=null;
		FileInputStream fis=new FileInputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("ORG");
		int lastRow = sh.getLastRowNum();
		Random r=new Random();	
		String BROWSER = test.getParameter("browser");
		String URL = test.getParameter("url");
		String USERNAME = test.getParameter("username");
		String PASSWORD = test.getParameter("password");
		if (BROWSER.contains("chrome")) {
			driver=new ChromeDriver();
		}else if (BROWSER.contains("firefox")) {
			driver=new FirefoxDriver();
		} else if(BROWSER.contains("edge")){
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		Actions action=new Actions(driver);
		for(int i=0;i<lastRow;i++) {
		
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			int num = r.nextInt(500);
			String Data = sh.getRow(1).getCell(2).getStringCellValue()+num;
			driver.findElement(By.name("accountname")).sendKeys(Data);
			driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
			Thread.sleep(2000);
			//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
			
			
			
		}
		WebElement AdminButton = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		action.moveToElement(AdminButton).perform();
		driver.findElement(By.partialLinkText("Sign")).click();
		driver.quit();
		
		

	}
	
		
}
