package tp3.gr1.insertData;

import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import tp3.gr1.dao.CarreraDAO;
import tp3.gr1.dao.EstudianteDAO;
import tp3.gr1.dao.MatriculaDAO;

public class Insert {

	public static void main(String[] args) {
		cargarCarreras();
	}
	
	public static void cargarCarreras() {
		
		/*
		 * A) Dar de alta un estudiante
		 * B) Matricular un estudiante en una carrera
		 */
		
		try {
			CSVParser carrerasCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/carreras.csv"));
			CarreraDAO carreras = new CarreraDAO();
			carreras.agregarCarreras(carrerasCSV);
			
			CSVParser estudiantesCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/estudiantes.csv"));
			EstudianteDAO estudiantes = new EstudianteDAO();
			estudiantes.agregarEstudiantes(estudiantesCSV);
			
			
			CSVParser matriculasCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/matriculas.csv"));
			MatriculaDAO matriculas = new MatriculaDAO();
			matriculas.agregarMatriculas(matriculasCSV);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
