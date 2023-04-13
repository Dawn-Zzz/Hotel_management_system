package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectDatabase {
	public static Connection connection() {
		Connection connection = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			connection = DriverManager.getConnection("jdbc:mySQL://localhost:3306/hotel","root","matkhau123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public static void Disconnection (Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
