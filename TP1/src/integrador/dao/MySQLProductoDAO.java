package integrador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import integrador.clases.ProductoDTO;
import integrador.interfaces.ProductoDAO;

public class MySQLProductoDAO implements ProductoDAO {

	private Connection conn;
	
	public MySQLProductoDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	private ProductoDTO convertir(ResultSet rs) throws SQLException {
		int idProducto = rs.getInt("idProducto");
		String nombre = rs.getString("nombre");
		int valor = rs.getInt("valor");
		ProductoDTO producto = new ProductoDTO(idProducto, nombre, valor);
		return producto;
	}

	@Override
	public ProductoDTO obtenerProductoMasRecaudo() {
		// TODO Auto-generated method stub
		String sentencia = "select *\n" + 
				"from Producto p\n" + 
				"where p.idProducto = (\n" + 
				"	select t2.idProducto\n" + 
				"	from (\n" + 
				"		select t.idProducto, max(t.cantidadesProductos) as cantidadesProductos\n" + 
				"		from (\n" + 
				"			select fp.idProducto as idProducto, p2.valor * sum(cantidad) as cantidadesProductos\n" + 
				"			from Factura_Producto fp \n" + 
				"			join Producto p2 on (p2.idProducto = fp.idProducto)\n" + 
				"			group by fp.idProducto)\n" + 
				"			AS t\n" + 
				"		group by t.idProducto\n" + 
				"		order by cantidadesProductos DESC \n" + 
				"		limit 1\n" + 
				"	) as t2\n" + 
				")";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductoDTO p = null;
		try {
			ps = conn.prepareStatement(sentencia);
			rs = ps.executeQuery();
			if (rs.next()) {
				p = convertir(rs);
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return p;
	}

	@Override
	public void agregarProducto(ProductoDTO p) {
		// TODO Auto-generated method stub
		String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(insert);
			ps.setInt(1, p.getIdProducto());
			ps.setString(2, p.getNombre());
			ps.setFloat(3, p.getValor());
			ps.executeUpdate();//
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}
	}

	@Override
	public void agregarProductos(CSVParser c) {
		// TODO Auto-generated method stub
		for(CSVRecord row: c) {
			try {
				this.agregarProducto(new ProductoDTO(Integer.parseInt(row.get("idProducto")), row.get("nombre"), Float.parseFloat(row.get("valor"))));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
