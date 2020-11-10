package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

	protected Connection conn;
	
	private final String driver = "com.mysql.cj.jdbc.Driver";
	private final String uri = "jdbc:mysql://localhost:3306/exampleDB";
	
	private final String user = "root";
	private final String password = "password";
	
	public void connect() {
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
			
			try {
				this.conn = DriverManager.getConnection(uri, user, password);
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void disconnect() throws SQLException {
		if (this.conn != null) {
			if (!this.conn.isClosed()) {
				this.conn.close();
			}
		}
	}
	
}
