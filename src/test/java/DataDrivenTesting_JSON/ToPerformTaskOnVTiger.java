package DataDrivenTesting_JSON;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ToPerformTaskOnVTiger {
	@Test
	public void toPerformOnVTiger() throws FileNotFoundException, IOException, ParseException, Exception {
		
		WebDriver driver=null;
		FileInputStream fis=new FileInputStream("./src/test/resources/Test_Data/Test_Script_Data.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("ORG");
		int lastRow = sh.getLastRowNum();
		Random r=new Random();
		
		JSONParser parser=new JSONParser();
		Object obj = parser.parse(new FileReader("./src/test/resources/Test_Data/VTiger.json"));
		JSONObject map=(JSONObject)obj;
		String BROWSER = map.get("browser").toString();
		String URL = map.get("url").toString();
		String USERNAME = map.get("username").toString();
		String PASSWORD = map.get("password").toString();
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
