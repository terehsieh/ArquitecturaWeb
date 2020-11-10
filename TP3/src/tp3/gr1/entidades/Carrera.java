package tp3.gr1.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Carrera {

	@Id 
	private int id_carrera;

	@Column(nullable = false)
	private String nombre_carrera;

	@JsonIgnore
	@OneToMany(mappedBy = "carrera")
	private List<Matricula> estudiantes;

	public Carrera(int id_carrera, String nombre_carrera, List<Matricula> estudiantes) {
		super();
		this.id_carrera = id_carrera;
		this.nombre_carrera = nombre_carrera;
		this.estudiantes = estudiantes;
	}

	public Carrera(int id_carrera, String nombre_carrera) {
		super();
		this.id_carrera = id_carrera;
		this.nombre_carrera = nombre_carrera;

	}

	public Carrera() {
		super();
	}

	public Carrera(Carrera carrera) {
		// TODO Auto-generated constructor stub
		this.id_carrera = carrera.id_carrera;
		this.nombre_carrera = carrera.nombre_carrera;
	}

	public int getId_Carrera() {
		return id_carrera;
	}

	public void setId_Carrera(int id_carrera) {
		this.id_carrera = id_carrera;
	}

	public String getNombre_carrera() {
		return nombre_carrera;
	}

	public void setNombre_carrera(String nombre_carrera) {
		this.nombre_carrera = nombre_carrera;
	}

	public List<Matricula> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Matricula> estudiantes) {
		this.estudiantes = estudiantes;
	}

	@Override
	public String toString() {
		return "Carrera [id_carrera=" + id_carrera + ", nombre_carrera=" + nombre_carrera +  "]";
	}

}
