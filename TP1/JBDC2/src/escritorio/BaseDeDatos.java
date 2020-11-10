package escritorio;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDeDatos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
		String uri = "jdbc:mysql://localhost:3306/exampleDB";
		
		try {
			Connection conn = DriverManager.getConnection(uri, "root", "password");
			
			conn.setAutoCommit(false);
			
			createTables(conn);
			
			addPerson(conn, 1, "Juan", 20);
			addPerson(conn, 2, "Paula", 20);
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void addPerson(Connection conn, int i, String string, int j) throws SQLException {
		// TODO Auto-generated method stub
		String insert = "INSERT INTO persona(id, nombre, edad) VALUES (?,?,?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, i);
		ps.setString(2, string);
		ps.setInt(3, j);
		ps.executeUpdate();
		ps.close();
		conn.commit();
	}

	private static void createTables(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		String table = "CREATE TABLE persona(id INT, nombre VARCHAR(500), edad INT, PRIMARY KEY(id))";
		
		conn.prepareStatement(table).execute();
		conn.commit();
		
	}

}
