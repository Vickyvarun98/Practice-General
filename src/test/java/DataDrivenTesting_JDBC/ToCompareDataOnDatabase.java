package DataDrivenTesting_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;



public class ToCompareDataOnDatabase {

	public static void main(String[] args) throws SQLException {
		String expected = "varun";
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
		Statement stat = conn.createStatement();
		ResultSet result = stat.executeQuery("select * from marks;");
		while(result.next()) {
		String actual = result.getString(2);
		if (actual.equalsIgnoreCase(expected)) {
			System.out.println(result.getString(1)+"\t"+result.getString(3));
			
		}
		}
		conn.close();
	
	}

}
