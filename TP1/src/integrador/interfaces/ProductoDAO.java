package integrador.interfaces;

import org.apache.commons.csv.CSVParser;

import integrador.clases.ProductoDTO;

public interface ProductoDAO {

	public ProductoDTO obtenerProductoMasRecaudo();
	
	public void agregarProducto(ProductoDTO p);
	
	public void agregarProductos(CSVParser c);
	
}
