package DataDrivenTesting_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class ToCheckGUIDataWithDatabaseData {

	// public static void main(String[] args) {
	@Test
	public void checkDataWithDatabase() throws SQLException {
		
		//Pass data On GUI
		String USERNAME = System.getProperty("username");
		String BROWSER = System.getProperty("browser");
		String URL = System.getProperty("url");
		String PASSWORD = System.getProperty("password");
		String DATA = System.getProperty("data");
		String STAFF = System.getProperty("staff");

		WebDriver driver = null;
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[contains(text(),'Sign')]")).click();
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Create')]")).click();
		driver.findElement(By.name("projectName")).sendKeys(DATA);
		driver.findElement(By.name("createdBy")).sendKeys(STAFF);
		WebElement Dropdown = driver.findElement(By.name("status"));
		Select drop=new Select(Dropdown);
		drop.selectByValue("Created");
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		
		
		//Check data with Database
		boolean flag=false;
		Driver Driver=new Driver();
		DriverManager.registerDriver(Driver);
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects","root@%","root");
		Statement stat = conn.createStatement();
		ResultSet result = stat.executeQuery("select * from project;");
		while (result.next()) {
			String Actual = result.getString(4);
			if (Actual.contains(DATA)) {
				flag=true;
				System.out.println("Data is UPDATED....");
			}
			
			
		}
		if (flag==false) {
			System.out.println("Data is not UPDATED....");
		
		}
		
		driver.quit();
	}

}
