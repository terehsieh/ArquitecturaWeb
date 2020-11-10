package tp3.gr1.entidades;

import java.math.BigDecimal;
import java.math.BigInteger;

public class InfoCarrera {
	
	private String nombre_carrera;


	private int fecha;
	
	private BigInteger cantInscriptos;
	
	private BigDecimal cantGraduados;


	public InfoCarrera(String nombre_carrera, int fecha, BigInteger cant_inscriptos,
			BigDecimal cantGraduados) {
		super();
		this.nombre_carrera = nombre_carrera;
		this.fecha = fecha;
		this.cantInscriptos = cant_inscriptos;
		this.cantGraduados = cantGraduados;
	}



	@Override
	public String toString() {
		return "InfoCarrera [nombre_carrera=" + nombre_carrera + ", fecha=" + fecha + ", cantInscriptos="
				+ cantInscriptos + ", cantGraduados=" + cantGraduados + "]";
	}



	public String getNombre_carrera() {
		return nombre_carrera;
	}


	public void setNombre_carrera(String nombre_carrera) {
		this.nombre_carrera = nombre_carrera;
	}


	public int getFecha() {
		return fecha;
	}


	public void setFecha(int fecha) {
		this.fecha = fecha;
	}


	public BigInteger getCantInscriptos() {
		return cantInscriptos;
	}


	public void setCantInscriptos(BigInteger cantInscriptos) {
		this.cantInscriptos = cantInscriptos;
	}


	public BigDecimal getCantGraduados() {
		return cantGraduados;
	}


	public void setCantGraduados(BigDecimal cantGraduados) {
		this.cantGraduados = cantGraduados;
	}
	

	
}
