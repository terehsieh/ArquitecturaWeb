package integrador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import integrador.clases.ClienteDTO;
import integrador.clases.ProductoDTO;
import integrador.interfaces.ClienteDAO;

public class MySQLClienteDAO implements ClienteDAO {
	
	private Connection conn;
	
	public MySQLClienteDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public ClienteDTO obtenerCliente(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarCliente(ClienteDTO c) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO Cliente (idCliente, nombre, email) VALUES (?,?,?)");
			ps.setInt(1, c.getIdCliente());
			ps.setString(2,c.getNombre());
			ps.setString(3, c.getEmail());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	@Override
	public void agregarClientes(CSVParser c) {
		// TODO Auto-generated method stub
		for(CSVRecord row: c) {
			this.agregarCliente(new ClienteDTO(Integer.parseInt(row.get("idCliente")), row.get("nombre"), row.get("email")));
		}
	}
	
	private ClienteDTO convertir(ResultSet rs) throws SQLException {
		// Conversion del ResultSet al objeto
		int idCliente = rs.getInt("idCliente");
		String nombre = rs.getString("nombre");
		String email = rs.getString("email");
		return new ClienteDTO(idCliente, nombre, email);
	}

	@Override
	public List<ClienteDTO> obtenerClientesPorFacturacion() {
		// Obtengo los clientes
		String sentencia = "select t1.idCliente, t1.nombre, t1.email, sum(t1.valorCompraProducto) as valorCompraTotal\n" + 
				"from (\n" + 
				"	select c.*, fp.cantidad * p.valor as valorCompraProducto\n" + 
				"	from Cliente c \n" + 
				"	join Factura f on (f.idCliente = c.idCliente)\n" + 
				"	join Factura_Producto fp on (fp.idFactura = f.idFactura)\n" + 
				"	join Producto p on (p.idProducto = fp.idProducto)\n" + 
				"	order by c.idCliente, p.idProducto ASC\n" + 
				"	) as t1\n" + 
				"GROUP by t1.idCliente\n" + 
				"ORDER by valorCompraTotal desc";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		try {
			ps = conn.prepareStatement(sentencia);
			rs = ps.executeQuery();
			while (rs.next()) {
				clientes.add(convertir(rs));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		return clientes;
	}

}
