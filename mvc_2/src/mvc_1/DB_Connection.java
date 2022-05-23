package mvc_1;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
	
	public static Connection getConnection() throws Exception{
		String connectionUrl ="jdbc:sqlserver://DESKTOP-M3R6AKR;databaseName=students;integratedSecurity=true;";
		Connection connection = null;
		try{
			connection = DriverManager.getConnection(connectionUrl);
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Connected to Database");
			Statement st = connection.createStatement();
			//connection.close();
		}		
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		return connection;
	}
}