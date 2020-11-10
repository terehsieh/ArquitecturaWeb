package tp3.gr1.entidades;

import java.math.BigDecimal;

public class ReporteGraduadosCarrerasPorAnio {

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


	public int getFechaGraduacion() {
		return fechaGraduacion;
	}


	public void setFechaGraduacion(int fechaGraduacion) {
		this.fechaGraduacion = fechaGraduacion;
	}


	public BigDecimal getCantGraduados() {
		return cantGraduados;
	}


	public void setCantGraduados(BigDecimal cantGraduados) {
		this.cantGraduados = cantGraduados;
	}


	private int id_carrera;


	private String nombre_carrera;


	private int fechaGraduacion;
	

	private BigDecimal cantGraduados;


	public ReporteGraduadosCarrerasPorAnio(int id_carrera, String nombre_carrera, int fechaGraduacion,
			BigDecimal o) {
		super();
		this.id_carrera = id_carrera;
		this.nombre_carrera = nombre_carrera;
		this.fechaGraduacion = fechaGraduacion;
		this.cantGraduados = o;
	}


	@Override
	public String toString() {
		return "ReporteGraduadosCarrerasPorAnio [id_carrera=" + id_carrera + ", nombre_carrera=" + nombre_carrera
				+ ", fechaGraduacion=" + fechaGraduacion + ", cantGraduados=" + cantGraduados + "]";
	}
	
	
	
}
