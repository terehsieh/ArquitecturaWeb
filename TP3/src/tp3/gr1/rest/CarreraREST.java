package tp3.gr1.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tp3.gr1.entidades.Carrera;

@Path("/carreras")
public class CarreraREST {
	
	@GET
	  @Path("/listaCarreras")
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Carrera> getListaCarreras() {
	      return LectorCicloVida.carrera.getCarreras();
	  }
	
	
	/*
	 * Resolucion ejercicio 2.F
	 */
	
	@GET
	@Path("/listaCarrerasInscriptos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carrera> getListaCarrerasInscriptos() {
		return LectorCicloVida.carrera.getCarrerasPorInscriptos();
	}
	
}
