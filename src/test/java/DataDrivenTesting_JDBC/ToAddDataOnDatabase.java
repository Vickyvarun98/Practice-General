package DataDrivenTesting_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ToAddDataOnDatabase {

	public static void main(String[] args) throws SQLException {
		// load from database
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		// connection to database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
		// create statment
		Statement state = conn.createStatement();
		// Update query
		int Update = state.executeUpdate("insert into marks values('S009','VARUN',7687461345);");
		System.out.println(Update);
		if (Update == 0) {
			System.out.println("Values are not updated....");
		} else {
			System.out.println("Values are updated....");

		}
		// close the database
		conn.close();

	}

}
