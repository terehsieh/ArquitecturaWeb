package integrador.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import integrador.interfaces.ClienteDAO;
import integrador.interfaces.FacturaDAO;
import integrador.interfaces.FacturaProductoDAO;
import integrador.interfaces.ProductoDAO;

public class MySQLDAOFactory extends DAOFactory {
	
	private ClienteDAO clientes = null;
	private FacturaDAO facturas = null;
	private FacturaProductoDAO facturasProductos = null;
	private ProductoDAO productos = null;
	
	private Connection conn = null;
	
	private final String uri = "jdbc:mysql://localhost:3306/db_integrador";
	
	public MySQLDAOFactory(String usuario, String clave) throws SQLException {
		// Se crea la conexion con los parametros y deshabilitacion de auto commit
		this.conn = DriverManager.getConnection(uri, usuario, clave);
		conn.setAutoCommit(false);
	}
	
	@Override
	public ClienteDAO obtenerClienteDAO() {
		if (this.clientes == null)
			return new MySQLClienteDAO(conn);
		return clientes;
	}

	@Override
	public FacturaDAO obtenerFacturaDAO() {
		if (this.facturas == null)
			return new MySQLFacturaDAO(conn);
		return this.facturas;
	}

	@Override
	public FacturaProductoDAO obtenerFacturaProductoDAO() {
		if (this.facturasProductos == null)
			return new MySQLFacturaProductoDAO(conn);
		return this.facturasProductos;
	}

	@Override
	public ProductoDAO obtenerProductoDAO() {
		if (this.productos == null)
			return new MySQLProductoDAO(conn);
		return this.productos;
	}

	@Override
	public void crearTablas() {
		try {
			
		PreparedStatement ps = null;
		String tablaCliente = "CREATE TABLE IF NOT EXISTS Cliente (\n" + 
					"    idCliente int NOT NULL,\n" + 
					"    nombre varchar(500) NOT NULL,\n" + 
					"    email varchar(150) NOT NULL,\n" + 
					"    CONSTRAINT Cliente_pk PRIMARY KEY (idCliente)\n" + 
					")";
		
		ps = conn.prepareStatement(tablaCliente);
		ps.execute();
		
		PreparedStatement ps2 = null;
		String tablaFactura = "CREATE TABLE IF NOT EXISTS Factura (\n" + 
				"    idFactura int NOT NULL,\n" + 
				"    idCliente int NOT NULL,\n" + 
				"    CONSTRAINT Factura_pk PRIMARY KEY (idFactura)\n" + 
				")";
		ps2 = conn.prepareStatement(tablaFactura);
		ps2.execute();
		
		PreparedStatement ps3 = null;
		String tablaFacturaProducto = "CREATE TABLE IF NOT EXISTS Factura_Producto (\n" + 
				"    idProducto int NOT NULL,\n" + 
				"    idFactura int NOT NULL,\n" + 
				"    cantidad int NOT NULL,\n" + 
				"    CONSTRAINT Factura_Producto_pk PRIMARY KEY (idProducto,idFactura)\n" + 
				")";
		conn.prepareStatement(tablaFacturaProducto).execute();
		ps3 = conn.prepareStatement(tablaFacturaProducto);
		ps3.execute();
		
		PreparedStatement ps4 = null;
		String tablaProducto = "CREATE TABLE IF NOT EXISTS Producto (\n" + 
				"    idProducto int NOT NULL,\n" + 
				"    nombre varchar(45) NOT NULL,\n" + 
				"    valor float NOT NULL,\n" + 
				"    CONSTRAINT Producto_pk PRIMARY KEY (idProducto)\n" + 
				")";
		
		ps4 = conn.prepareStatement(tablaProducto);
		ps4.execute();
		
		PreparedStatement ps5 = null;
		String relacion_Factura_Cliente = "ALTER TABLE Factura ADD CONSTRAINT Factura_Cliente FOREIGN KEY Factura_Cliente (idCliente)\n" + 
				"    REFERENCES Cliente (idCliente)";
		ps5 = conn.prepareStatement(relacion_Factura_Cliente);
		ps5.execute();
		
		String relacionFacturaProductoFactura = "ALTER TABLE Factura_Producto ADD CONSTRAINT Factura_Producto_Factura FOREIGN KEY Factura_Producto_Factura (idFactura)\n" + 
				"    REFERENCES Factura (idFactura)";
		conn.prepareStatement(relacionFacturaProductoFactura).execute();
		
		PreparedStatement ps6 = null;
		String relacionFacturaProducto = "ALTER TABLE Factura_Producto ADD CONSTRAINT Factura_Producto_Producto FOREIGN KEY Factura_Producto_Producto (idProducto)\n" + 
				"    REFERENCES Producto (idProducto)";
		ps6 = conn.prepareStatement(relacionFacturaProducto);
		ps6.execute();
	
		conn.commit();
		
		ps.close();
		ps2.close();
		ps3.close();
		ps4.close();
		ps5.close();
		ps6.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
