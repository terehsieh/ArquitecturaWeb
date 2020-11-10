package controller;

import java.io.Serializable;
import java.util.List;
import java.sql.Date;  

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entidades.Carrera;
import entidades.Estudiante;
import entidades.Matricula;

public class MatriculaController implements Serializable {

	private static final long serialVersionUID = -6284052128342094661L;

	private EntityManagerFactory emf = null;

	public MatriculaController() {
		this.emf = Persistence.createEntityManagerFactory("Example");
	}
	
	public void insert(Matricula matricula) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(matricula);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// Fijar que no se haya creado la matricula. Controlar:id estudiante, id carrera
	public Matricula getMatricula(Matricula matricula) {
		EntityManager em = emf.createEntityManager();
		Query query = em
				.createNativeQuery(
						"SELECT * FROM Matricula M WHERE M.id_estudiante =:id_estu AND M.id_carrera =:id_car ",
						Matricula.class)
				.setParameter("id_estu", matricula.getEstudiante().getLegajo())
				.setParameter("id_car", matricula.getCarrera().getId_Carrera());
		List<Matricula> listado = query.getResultList();
		if (listado.size() == 0) {
			return null;
		} else {
			return listado.get(0);
		}
	}
	
	public void agregarMatriculas(CSVParser c) {
		// TODO Auto-generated method stub
		for(CSVRecord row: c) {
			EntityManager em = emf.createEntityManager();
			Query query = em
					.createNativeQuery(
							"SELECT * FROM Estudiante e WHERE e.legajo =:id_estu",
							Estudiante.class)
					.setParameter("id_estu", Integer.parseInt(row.get("id_estudiante")));
			List<Estudiante> estudiante = query.getResultList();
			Estudiante e = estudiante.get(0);
			
			
			
			int id = Integer.parseInt(row.get("id_carrera"));
			EntityManager em2 = emf.createEntityManager();
			Query query2 = em2
					.createNativeQuery(
							"SELECT * FROM Carrera c WHERE c.id_carrera =:id_carr",
							Carrera.class)
					.setParameter("id_carr", id);
			List<Carrera> carrera = query2.getResultList();
			Carrera carr = carrera.get(0);
			
			String fecha_inscripcion = String.valueOf(row.get("fecha_inscripcion"));
			String fecha_graduacion = String.valueOf(row.get("fecha_graduacion"));
			
			java.sql.Date date1 = java.sql.Date.valueOf(fecha_inscripcion);//converting string into sql date
			java.sql.Date date2 = java.sql.Date.valueOf(fecha_graduacion);//converting string into sql date
			
			boolean b = Boolean.parseBoolean(row.get("finalizo"));
			Matricula m = new Matricula(e, carr, date1, date2, b);
			this.insert(m);	
		}
	}
	
	
}

