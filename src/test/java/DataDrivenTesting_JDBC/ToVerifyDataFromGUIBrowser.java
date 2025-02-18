package DataDrivenTesting_JDBC;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.mysql.cj.jdbc.Driver;

public class ToVerifyDataFromGUIBrowser {

	public static void main(String[] args) throws SQLException, IOException {
		Driver Driver=new Driver();
		DriverManager.registerDriver(Driver);
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects","root@%","root");
		Statement stat = conn.createStatement();
		int res = stat.executeUpdate("insert into project values ('TY_PROJ_010','Arun','05/10/2024','Flipkart_0016','Created',6);");
		if (res==0) {
			System.out.println("Value is not updated in DATABASE");
		}else {
			System.out.println("Value is updated in DATABASE");
		}
		
		System.out.println();
		System.out.println();
		
		//To verify in browser
		
		WebDriver driver = null;
		FileInputStream fis=new FileInputStream("./src/test/resources/Test_Data/Ninza.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String URL = prop.getProperty("url");
		String BROWSER = prop.getProperty("browser");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		String DATA = prop.getProperty("data");
		
		
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
		driver.findElement(By.xpath("//input[@placeholder='Search by Project Id']")).sendKeys(DATA);
		String actual = driver.findElement(By.xpath("//table[@class='table table-striped table-hover']")).getText();
		if (actual.contains(DATA)) {
			System.out.println("Data is added to GUI");
		}else {
			System.out.println("Data is not added to GUI");
		}
		driver.quit();
		
		
		
		
		
		

	}

}
