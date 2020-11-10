package integrador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import integrador.clases.FacturaDTO;
import integrador.interfaces.FacturaDAO;

public class MySQLFacturaDAO implements FacturaDAO {
	
	private Connection conn;

	public MySQLFacturaDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public FacturaDTO obtenerFactura(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarFactura(FacturaDTO f) {
		// TODO Auto-generated method stub
		String insert = "INSERT INTO Factura (idFactura,idCliente) VALUES (?,?)";
		PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(insert);
				ps.setInt(1, f.getIdFactura());
				ps.setInt(2, f.getIdCliente());
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
	public List<FacturaDTO> obtenerFacturas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarFacturas(CSVParser c) {
		// TODO Auto-generated method stub
		for(CSVRecord row: c) {
			this.agregarFactura(new FacturaDTO(Integer.parseInt(row.get("idFactura")), Integer.parseInt(row.get("idCliente"))));
		}
	}

}
