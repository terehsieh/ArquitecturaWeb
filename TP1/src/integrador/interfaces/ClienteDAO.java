package integrador.interfaces;

import java.util.List;

import org.apache.commons.csv.CSVParser;

import integrador.clases.ClienteDTO;

public interface ClienteDAO {
	
	public ClienteDTO obtenerCliente(int id);
	
	public void agregarCliente(ClienteDTO c);
	
	public void agregarClientes(CSVParser c);
	
	public List<ClienteDTO> obtenerClientesPorFacturacion();
}
