package integrador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import integrador.clases.FacturaProductoDTO;
import integrador.interfaces.FacturaProductoDAO;

public class MySQLFacturaProductoDAO implements FacturaProductoDAO {
	
	private Connection conn;

	public MySQLFacturaProductoDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public FacturaProductoDTO obtenerCliente(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void agregarFacturasProductos(CSVParser c) {
		// TODO Auto-generated method stub
		for(CSVRecord row: c) {
			this.agregarFacturaProducto(new FacturaProductoDTO(Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idProducto")), Integer.parseInt(row.get("cantidad"))));
		}
	}

	@Override
	public void agregarFacturaProducto(FacturaProductoDTO f) {
		// TODO Auto-generated method stub
		String insert = "INSERT INTO Factura_Producto (idProducto, idFactura, cantidad) VALUES (?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(insert);
			ps.setInt(1, f.getIdProducto());
			ps.setInt(2, f.getIdFactura());
			ps.setInt(3, f.getCantidad());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
		}
	}

	@Override
	public List<FacturaProductoDTO> obtenerFacturaProductos() {
		// TODO Auto-generated method stub
		return null;
	}

}
