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
public class Estudiante {
	
	@Id 
	private int legajo;
	
	@Column (nullable=false)
	private String nombre;
	
	@Column (nullable=false)
	private String apellido;
	
	@Column (nullable=false)
	private int edad;

	@Column (nullable=false)
	private String genero;

	@Column (nullable=false, unique=true)
	private int dni;
	
	@Column (nullable=false)
	private String ciudad_residencia;
	
	@JsonIgnore
	@OneToMany(mappedBy = "estudiante")
	private List<Matricula> carreras;


	public Estudiante() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Crea el estudiante con las carreras
	public Estudiante(int legajo, String nombre, String apellido, int edad, String genero, int dni,
			String ciudad_residencia, List<Matricula> carreras) {
		super();
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudad_residencia = ciudad_residencia;
	}
	
	// Crea el estudiante, sin ninguna carrera
	public Estudiante(int legajo, String nombre, String apellido, int edad, String genero, int dni,
			String ciudad_residencia) {
		super();
		this.legajo = legajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudad_residencia = ciudad_residencia;
	}
	
	public Estudiante(Estudiante estudiante) {
		// TODO Auto-generated constructor stub
		this.legajo = estudiante.legajo;
		this.nombre = estudiante.nombre;
		this.apellido = estudiante.apellido;
		this.edad = estudiante.edad;
		this.genero = estudiante.genero;
		this.dni = estudiante.dni;
		this.ciudad_residencia = estudiante.ciudad_residencia;
	}

	public int getLegajo() {
		return legajo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getCiudad_residencia() {
		return ciudad_residencia;
	}

	public void setCiudad_residencia(String ciudad_residencia) {
		this.ciudad_residencia = ciudad_residencia;
	}

	public List<Matricula> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<Matricula> carreras) {
		this.carreras = carreras;
	}
	// Agrega la matriculacion a una carrera
	public void addCarrera(Matricula carrera) {
		this.carreras.add(carrera);
	}

	@Override
	public String toString() {
		return "Estudiante [legajo=" + legajo + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
				+ ", genero=" + genero + ", dni=" + dni + ", ciudad_residencia=" + ciudad_residencia+"]";
	}


}
