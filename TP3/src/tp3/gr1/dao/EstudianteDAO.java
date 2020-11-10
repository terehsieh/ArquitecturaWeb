package tp3.gr1.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import tp3.gr1.entidades.Estudiante;


public class EstudianteDAO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private EntityManagerFactory emf = null;
	//private EntityManager em = null;

	public EstudianteDAO() {
		this.emf = Persistence.createEntityManagerFactory("Example");
		//this.em = emf.createEntityManager();
	}

	public Estudiante getEstudianteDNI(int dni) {
		EntityManager em = emf.createEntityManager();
		// el select devuelve un listado por mas q sea uno
		List<Estudiante> listado = em.createQuery("SELECT E FROM Estudiante E WHERE E.dni =:dni ", Estudiante.class)
				.setParameter("dni", dni).getResultList();
		if (listado.size() == 0) {// no existe el estudiante
			return null;
		} else {
			return listado.get(0);
		}
	}
	
	public int getUltimoId() {
		EntityManager em = emf.createEntityManager();
		List<Estudiante> listado = em.createQuery("SELECT E FROM Estudiante E order by E.legajo desc ", Estudiante.class).getResultList();
		return listado.get(0).getLegajo();
	}

	public void insert(Estudiante estudiante) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			if (this.getEstudianteDNI(estudiante.getDni()) != null) {
				System.out.println("Ya se encuentra el estudiante con el DNI: " + estudiante.getDni());
			} else {
				em.getTransaction().begin();
				em.persist(estudiante);
				em.getTransaction().commit();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public List<Estudiante> getEstudiantesSortDNI() {
		EntityManager em = emf.createEntityManager();
		List<Estudiante> listado = em.createQuery("SELECT E FROM Estudiante E ORDER BY E.dni ASC ", Estudiante.class)
				.getResultList();

		return listado;
	}
	
	public Estudiante getEstudianteLegajo(int legajo) {
		EntityManager em = emf.createEntityManager();
		// el select devuelve un listado por mas q sea uno
		List<Estudiante> listado = em.createQuery("SELECT E FROM Estudiante E WHERE E.legajo =:legajo ", Estudiante.class)
				.setParameter("legajo", legajo).getResultList();
		if (listado.size() == 0) {// no existe el estudiante
			return null;
		} else {
			return listado.get(0);
		}
	}
	
	public 	List<Estudiante> getEstudianteGenero(String genero) {
		EntityManager em = emf.createEntityManager();
		Query query =em.createNativeQuery("SELECT * FROM Estudiante E WHERE E.genero =:genero ", Estudiante.class)
				.setParameter("genero", genero);
		List<Estudiante> listado = query.getResultList();
		return listado;
	}
	
	public 	List<Estudiante> getEstudiantesCarreraResidencia(String carrera, String residencia) {
		EntityManager em = emf.createEntityManager();
		Query query =em.createNativeQuery("SELECT e.* FROM Estudiante e JOIN Matricula m ON (e.legajo=m.id_estudiante) JOIN Carrera c ON (m.id_carrera=c.id_carrera) WHERE c.nombre_carrera =:nombre_carrera AND e.ciudad_residencia=:ciudad_residencia ", Estudiante.class)
				.setParameter("nombre_carrera", carrera)
				.setParameter("ciudad_residencia", residencia);
		List<Estudiante> listado = query.getResultList();
		return listado;
	}

	public void agregarEstudiantes(CSVParser c) {
		// TODO Auto-generated method stub
		for(CSVRecord row: c) {
			this.insert(new Estudiante(Integer.parseInt(row.get("legajo")), row.get("nombre"), row.get("apellido"), Integer.parseInt(row.get("edad")), row.get("genero"), Integer.parseInt(row.get("dni")), row.get("ciudad_residencia")));
		}
	}
}
