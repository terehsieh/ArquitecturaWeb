package tp3.gr1.rest;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tp3.gr1.entidades.Carrera;
import tp3.gr1.entidades.Estudiante;
import tp3.gr1.entidades.InfoCarrera;
import tp3.gr1.entidades.Matricula;

@Path("/matriculas")
public class MatriculaREST {
	
	@POST
	@Path("/nuevaMatricula")
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public void addMatricula(@FormParam("id_estudiante") int id_estudiante, @FormParam("id_carrera") int id_carrera, @FormParam("fecha_inscripcion") String fecha_inscripcion, @FormParam("fecha_graduacion") String fecha_graduacion, @FormParam("esta_graduado") String esta_graduado) {
		Estudiante e = LectorCicloVida.estudiante.getEstudianteLegajo(id_estudiante);
		Carrera c = LectorCicloVida.carrera.getCarreraID(id_carrera);
			
		LocalDate fi = LocalDate.parse( fecha_inscripcion ) ;
		LocalDate fg = LocalDate.parse( fecha_graduacion ) ;
		
		java.sql.Date date1 = java.sql.Date.valueOf(fi);
		java.sql.Date date2 = java.sql.Date.valueOf(fg);
		
		Matricula m;
		if (esta_graduado != null)
			m = new Matricula(e, c, date1, date2, true);
		else
			m = new Matricula(e, c, date1, date2, false);
		LectorCicloVida.matricula.insert(m);
	}
	
	@GET
	@Path("/reporte")
	@Produces(MediaType.APPLICATION_JSON)
	public List<InfoCarrera> getReporte() {
		return LectorCicloVida.reporte.getReporte();
	}
}
