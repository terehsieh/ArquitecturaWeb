import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import controller.CarreraController;
import controller.EstudianteController;
import controller.MatriculaController;
import entidades.Carrera;
import entidades.Estudiante;
import entidades.Matricula;

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
			CarreraController carreras = new CarreraController();
			carreras.agregarCarreras(carrerasCSV);
			
			CSVParser estudiantesCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/estudiantes.csv"));
			EstudianteController estudiantes = new EstudianteController();
			estudiantes.agregarEstudiantes(estudiantesCSV);
			
			
			CSVParser matriculasCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/matriculas.csv"));
			MatriculaController matriculas = new MatriculaController();
			matriculas.agregarMatriculas(matriculasCSV);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
