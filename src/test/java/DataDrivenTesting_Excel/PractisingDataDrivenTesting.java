package DataDrivenTesting_Excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PractisingDataDrivenTesting {

	public static void main(String[] args) throws IOException, Exception {
		WebDriver driver=null;
		//From Property 
		FileInputStream fis=new FileInputStream("./src/test/resources/Test_Data/VTiger.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		//From Excel
		FileInputStream FIS=new FileInputStream("./src/test/resources/Test_Data/Test_Case_Data.xlsx");
		Workbook wb=WorkbookFactory.create(FIS);
		Sheet sheet = wb.getSheet("ORG");
		String Data = sheet.getRow(16).getCell(2).getStringCellValue();
		
		if (BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.contains("egde")) {
			driver = new EdgeDriver();
		}
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		Actions action=new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(Data);
		driver.findElement(By.xpath("(//input[@accesskey='S'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Organizations")).click();
		action.moveByOffset(0, 500).perform();
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshots/VTiger.jpeg");
		FileHandler.copy(src, dest);
		System.out.println("Screenshot is taken......");
		WebElement AdminButton = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wait.until(ExpectedConditions.visibilityOf(AdminButton));
		action.moveToElement(AdminButton).perform();
		WebElement signout = driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]"));
		
		action.moveToElement(signout).click().perform();
		for(;;) {
			try {
				signout.click();
				break;
			} catch (Exception e) {
				
			//	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]"))));
				
				//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]"))));
				
			}
			finally {
				driver.quit();
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
