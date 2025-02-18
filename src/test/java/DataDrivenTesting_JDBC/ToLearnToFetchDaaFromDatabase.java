package DataDrivenTesting_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ToLearnToFetchDaaFromDatabase {

	public static void main(String[] args) throws SQLException {
		//register or load database
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		//Connection to database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
		//Create statement
		Statement stat = conn.createStatement();
		//Execute Query for table
		ResultSet res = stat.executeQuery("select * from marks;");
		
		while (res.next()) {
			System.out.println(res.getString(1) +"  "+res.getString(2));
			System.out.println();
			
			
		}System.out.println("---EXECUTED---");
		
		
		conn.close();
		
		
	}

}
