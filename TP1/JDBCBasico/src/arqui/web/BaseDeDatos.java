package arqui.web;

import java.lang.reflect.InvocationTargetException;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sun.jdi.connect.spi.Connection;


public class BaseDeDatos {

	public static void main(String[] args) {
		/*
		 * Primero hay que registrar el driver
		 */
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
		/*
		 * Una vez configurado el driver nos conectamos a la base de datos
		 * Agregando datos a la uri que es la direccion de la base de datos.
		 * 
		 * Se arranca con jdbc que es el protocolo que se quiere usar
		 * derby por usar derby
		 * Ahora como se crea la base de datos de cero se le pone un nombre y un parametro
		 * despues del ; que si no existe la crea.
		 */
		
		String uri = "jdbc:derby:MyDerbyDb;create=true";
		
		/*
		 * Como la base de datos es enbebida no se necesita el metodo con usuario
		 * y contraseña.
		 */
		
		try {
			java.sql.Connection conn = DriverManager.getConnection(uri);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
