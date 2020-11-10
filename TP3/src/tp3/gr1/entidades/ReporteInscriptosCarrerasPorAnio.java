package tp3.gr1.entidades;

import java.math.BigInteger;


public class ReporteInscriptosCarrerasPorAnio {
	
	

	private int id_carrera;


	private String nombre_carrera;


	private int fechaInscripcion;
	

	private BigInteger cantInscriptos;


	public int getId_carrera() {
		return id_carrera;
	}


	public void setId_carrera(int id_carrera) {
		this.id_carrera = id_carrera;
	}


	public String getNombre_carrera() {
		return nombre_carrera;
	}


	public void setNombre_carrera(String nombre_carrera) {
		this.nombre_carrera = nombre_carrera;
	}


	public int getFechaInscripcion() {
		return fechaInscripcion;
	}


	public void setFechaInscripcion(int fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}


	public BigInteger getCantInscriptos() {
		return cantInscriptos;
	}


	public void setCantInscriptos(BigInteger cantInscriptos) {
		this.cantInscriptos = cantInscriptos;
	}


	public ReporteInscriptosCarrerasPorAnio(int id_carrera, String nombre_carrera, int fechaInscripcion, BigInteger cantInscriptos) {
		super();
		this.id_carrera = id_carrera;
		this.nombre_carrera = nombre_carrera;
		this.fechaInscripcion = fechaInscripcion;
		this.cantInscriptos = cantInscriptos;
	}


	public String toString() {
		return "ReporteInscriptosCarrerasPorAnio [id_carrera=" + id_carrera + ", nombre_carrera=" + nombre_carrera
				+ ", fechaInscripcion=" + fechaInscripcion + ", cantidadInscriptos=" + cantInscriptos + "]";
	}
	
	
	
	

}
