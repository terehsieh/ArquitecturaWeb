package integrador.demo;

import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import integrador.dao.DAOFactory;
import integrador.interfaces.ClienteDAO;
import integrador.interfaces.FacturaDAO;
import integrador.interfaces.FacturaProductoDAO;
import integrador.interfaces.ProductoDAO;

public class Demo {

	public static void main(String[] args) {
		
		/*
		 * Datos de la base de datos:
		 * Nombre: db_integrador
		 * Usuario: root (cambiarlo entre comillas)
		 * Clave: password (cambiarlo entre comillas);
		 * Java: JavaSE-11
		 */
		
		/*
		 * Elegir tipo de BD:
		 * 1 - MySQL
		 * 
		 */
		
		DAOFactory fabrica = DAOFactory.obtenerDAOFactory(1, "root", "password");
		
		/*
		 * Creación de tablas
		 */
		
		fabrica.crearTablas();
		
		/*
		 * Accesso a los objetos encapsulados
		 */
		
		ClienteDAO clientes = fabrica.obtenerClienteDAO();
		ProductoDAO productos = fabrica.obtenerProductoDAO();
		FacturaDAO facturas = fabrica.obtenerFacturaDAO();
		FacturaProductoDAO facturasProducto = fabrica.obtenerFacturaProductoDAO();

		/*
		 * Cargo la BD elegida con los archivos CSV
		 */
		cargarBD(fabrica, clientes, productos, facturas, facturasProducto);
		
		/*
		 * Resolución del ejercicio 3
		 */
		
		System.out.println("Producto que más recaudo: \n" + productos.obtenerProductoMasRecaudo());
		
		/*
		 * Resolución del ejercicio 4
		 */
		System.out.println("Lista de Clientes por la facturacion: \n" + clientes.obtenerClientesPorFacturacion());

	}

	private static void cargarBD(DAOFactory fabrica, ClienteDAO clientes, ProductoDAO productos, FacturaDAO facturas,
			FacturaProductoDAO facturasProducto) {
		// Carga de la BD con los archivos CSV pasados por parametro
		try {
			CSVParser clientesCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/clientes.csv"));
			clientes.agregarClientes(clientesCSV);
			
			CSVParser productosCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/productos.csv"));
			productos.agregarProductos(productosCSV);
			
			CSVParser facturasCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/facturas.csv"));
			facturas.agregarFacturas(facturasCSV);
			
			CSVParser facturasProductosCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/facturas-productos.csv"));
			facturasProducto.agregarFacturasProductos(facturasProductosCSV);	
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
