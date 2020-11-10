package tp3.gr1.entidades;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Matricula implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_estudiante", referencedColumnName = "legajo")
	private Estudiante estudiante;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera")
	private Carrera carrera;

	@Column(nullable = false)
	private Date fecha_inscripcion;

	@Column(nullable = true)
	private Date fecha_graduacion;

	@Column(nullable = true, name = "finalizo")
	private boolean finalizo;

	public Matricula() {
		super();
	}

	public Matricula(Estudiante estudiante, Carrera carrera, Date fecha_inscripcion) {
		super();
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.fecha_inscripcion = fecha_inscripcion;
	}
	
	public Matricula(Estudiante estudiante, Carrera carrera, Date fecha_inscripcion, Date fecha_graduacion,
			boolean finalizo) {
		super();
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.fecha_inscripcion = fecha_inscripcion;
		this.fecha_graduacion = fecha_graduacion;
		this.finalizo = finalizo;
	}

	public Matricula(Estudiante estudiante, Carrera carrera) {
		super();
		this.estudiante = estudiante;
		this.carrera = carrera;

	}


	public Date getFecha_inscripcion() {
		return fecha_inscripcion;
	}

	public void setFecha_inscripcion(Date fecha_inscripcion) {
		this.fecha_inscripcion = fecha_inscripcion;
	}

	public Date getFecha_graduacion() {
		return fecha_graduacion;
	}

	public void setFecha_graduacion(Date fecha_graduacion) {
		this.fecha_graduacion = fecha_graduacion;
	}

	public boolean isFinalizo() {
		return finalizo;
	}

	public void setFinalizo(boolean finalizo) {
		this.finalizo = finalizo;
	}

	@Override
	public String toString() {
		return "Matricula [estudiante=" + estudiante + ", carrera=" + carrera + ", fecha_inscripcion="
				+ fecha_inscripcion + ", fecha_graduacion=" + fecha_graduacion + ", finalizo=" + finalizo + "]";
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

}
