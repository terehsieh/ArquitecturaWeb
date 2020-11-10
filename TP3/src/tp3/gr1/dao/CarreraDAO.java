package tp3.gr1.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import tp3.gr1.entidades.Carrera;
import tp3.gr1.entidades.Estudiante;
import tp3.gr1.entidades.Matricula;
import tp3.gr1.entidades.ReporteGraduadosCarrerasPorAnio;
import tp3.gr1.entidades.ReporteInscriptosCarrerasPorAnio;

public class CarreraDAO implements Serializable {

	private static final long serialVersionUID = 902997133635722325L;
	private EntityManagerFactory emf = null;

	public CarreraDAO() {
		this.emf = Persistence.createEntityManagerFactory("Example");
	}

	public void insert(Carrera carrera) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			if (this.getCarreraID(carrera.getId_Carrera()) != null) {
				System.out.println("Ya se encuentra la carrera con el id: " + carrera.getId_Carrera());
			} else {
				em.getTransaction().begin();
				em.persist(carrera);
				em.getTransaction().commit();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public int getUltimoId() {
		EntityManager em = emf.createEntityManager();
		List<Carrera> listado = em.createQuery("SELECT C FROM Carrera C order by C.id_carrera desc ", Carrera.class).getResultList();
		return listado.get(0).getId_Carrera();
	}

	public List<Carrera> getCarreras() {
		EntityManager em = emf.createEntityManager();
		Query query = em
				.createNativeQuery("SELECT * FROM Carrera C", Carrera.class);
		 List<Carrera> listado = query.getResultList();
		return listado;
	}
	
	public Carrera getCarreraID(int id_carrera) {
		EntityManager em = emf.createEntityManager();
		List<Carrera> listado = em
				.createQuery("SELECT C FROM Carrera C WHERE C.id_carrera =:id_carrera ", Carrera.class)
				.setParameter("id_carrera", id_carrera).getResultList();
		if (listado.size() == 0) {// no existe el estudiante
			return null;
		} else {
			return listado.get(0);
		}
	}
	

	public List<ReporteInscriptosCarrerasPorAnio> getInscriptosPorCarrera() {
		EntityManager em = emf.createEntityManager();
		Query query = em
				.createNativeQuery("SELECT c.id_carrera, c.nombre_carrera ,extract(year from m.fecha_inscripcion) as fechaInscripcion, count(m.id_carrera) as CantInscriptos\n" + 
						"FROM Carrera c JOIN Matricula m ON (c.id_carrera = m.id_carrera)\n" + 
						"GROUP BY m.id_carrera, extract(YEAR FROM m.fecha_inscripcion) " +
						"ORDER BY c.nombre_carrera,extract(YEAR FROM m.fecha_inscripcion)");
		List<Object[]> join = query.getResultList();
		return join.stream().map(o -> new ReporteInscriptosCarrerasPorAnio((Integer)o[0], (String)o[1], (Integer)o[2], (BigInteger)o[3])).collect(Collectors.toList());
	}
	
	public List<ReporteGraduadosCarrerasPorAnio> getGraduadosPorCarrera() {
		EntityManager em = emf.createEntityManager();
		Query query = em
				.createNativeQuery("SELECT c.id_carrera, c.nombre_carrera ,extract(year from m.fecha_graduacion) as fechaGraduacion , SUM(m.finalizo) as cantGraduados\n" + 
						"FROM Carrera c JOIN Matricula m ON (c.id_carrera = m.id_carrera)\n" + 
						"GROUP BY m.id_carrera,extract(YEAR FROM m.fecha_graduacion) "+
						"ORDER BY c.nombre_carrera,extract(YEAR FROM m.fecha_graduacion)");
		List<Object[]> join = query.getResultList();
		return join.stream().map(o -> new ReporteGraduadosCarrerasPorAnio((Integer)o[0], (String)o[1], (Integer)o[2], (BigDecimal)o[3])).collect(Collectors.toList());
	}
	
	public List<Carrera> getCarrerasPorInscriptos() {
		EntityManager em = emf.createEntityManager();
		Query query = em
				.createNativeQuery("SELECT m.id_carrera, c.nombre_carrera FROM Carrera c JOIN Matricula m ON c.id_carrera=m.id_carrera GROUP BY m.id_carrera, c.nombre_carrera ORDER BY count(m.id_carrera) DESC", Carrera.class);
		List<Carrera> listado = query.getResultList();
		return listado;
	}
	//CSV

	public void agregarCarreras(CSVParser c) {
		for(CSVRecord row: c) {
			int id_carrera = Integer.parseInt(row.get("id_carrera"));
			String nombre_carrera = row.get("nombre_carrera");
			
			this.insert(new Carrera(id_carrera, nombre_carrera));
		}
	}


}
