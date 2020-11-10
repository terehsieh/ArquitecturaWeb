package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.DAOPersona;
import principal.Persona;

public class DAOPersonaImpl extends ConnectionDB implements DAOPersona{

	@Override
	public void agregarPersona(Persona p) {
		// TODO Auto-generated method stub
		try {
			this.connect();
			this.conn.setAutoCommit(false);
			
			String sentence = "INSERT INTO persona(id, nombre, edad) VALUES (?,?,?)";
			PreparedStatement ps = this.conn.prepareStatement(sentence);
			
			ps.setInt(1, p.getId());
			ps.setString(2, p.getNombre());
			ps.setInt(3, p.getEdad());
			ps.executeUpdate();
			ps.close();
			conn.commit();
			
			this.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public List<Persona> listarPersonas() {
		List<Persona> list = new ArrayList<Persona>();
		try {
			this.connect();
			
			String sentence = "SELECT * FROM persona";
			
			PreparedStatement ps = conn.prepareStatement(sentence);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Persona p = new Persona(rs.getInt(1), rs.getString(2), rs.getInt(3));
				list.add(p);
			}
			
			this.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
