package integrador.interfaces;

import java.util.List;

import org.apache.commons.csv.CSVParser;

import integrador.clases.FacturaProductoDTO;

public interface FacturaProductoDAO {
	
	public FacturaProductoDTO obtenerCliente(int id);
	
	public void agregarFacturaProducto(FacturaProductoDTO f);
	
	public void agregarFacturasProductos(CSVParser c);
	
	public List<FacturaProductoDTO> obtenerFacturaProductos();
}
