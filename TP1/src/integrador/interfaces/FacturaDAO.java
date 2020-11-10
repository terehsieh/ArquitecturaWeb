package integrador.interfaces;

import java.util.List;

import org.apache.commons.csv.CSVParser;

import integrador.clases.FacturaDTO;

public interface FacturaDAO {
	
	public void agregarFactura(FacturaDTO f);
	
	public void agregarFacturas(CSVParser c);
	
	public List<FacturaDTO> obtenerFacturas();

	public FacturaDTO obtenerFactura(int id);
}
