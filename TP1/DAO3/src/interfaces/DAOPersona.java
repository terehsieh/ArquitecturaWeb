package interfaces;

import java.util.List;

import principal.Persona;

public interface DAOPersona {
	public void agregarPersona(Persona p);
	public List<Persona> listarPersonas();
}
