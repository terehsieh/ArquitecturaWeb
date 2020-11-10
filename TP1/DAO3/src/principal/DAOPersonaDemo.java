package principal;

import dao.DAOPersonaImpl;
import interfaces.DAOPersona;

public class DAOPersonaDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Persona p1 = new Persona(3,"Federico",29);
		Persona p2 = new Persona(4,"Juan Ignacio",25);
		
		DAOPersona d = new DAOPersonaImpl();
		
		d.agregarPersona(p1);
		d.agregarPersona(p2);
		
		System.out.println(d.listarPersonas());
	}

}
