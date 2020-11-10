package tp3.gr1.rest;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import tp3.gr1.dao.CarreraDAO;
import tp3.gr1.dao.EstudianteDAO;
import tp3.gr1.dao.MatriculaDAO;
import tp3.gr1.entidades.ReporteCarrerasPorAnio;
import tp3.gr1.entidades.ReporteGraduadosCarrerasPorAnio;
import tp3.gr1.entidades.ReporteInscriptosCarrerasPorAnio;

/**
 * Servlet implementation class LectorCicloVida
 */

@WebListener
public class LectorCicloVida implements ServletContextListener {

	public static EstudianteDAO estudiante = null;
	public static MatriculaDAO matricula = null;
	public static CarreraDAO carrera = null;
	public static ReporteCarrerasPorAnio reporte = null;
	public static ReporteGraduadosCarrerasPorAnio graduadosCarreraAnio = null;
	public static ReporteInscriptosCarrerasPorAnio inscriptosCarreraAnio = null;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		estudiante = new EstudianteDAO();
		matricula = new MatriculaDAO();
		carrera = new CarreraDAO();
		List<ReporteGraduadosCarrerasPorAnio> lista= carrera.getGraduadosPorCarrera();
		List<ReporteInscriptosCarrerasPorAnio> lista2= carrera.getInscriptosPorCarrera();
		reporte = new ReporteCarrerasPorAnio(lista,lista2);
//			try {
//				Application.csv();
//			} catch (IOException ioException) {
//				System.out.print(ioException);
//			}

	}
}
