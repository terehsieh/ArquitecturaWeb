package integrador.clases;

public class ClienteDTO {
	private int idCliente;
	private String nombre;
	private String email;
	
	public ClienteDTO(int idCliente, String nombre, String email) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.email = email;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "[idCliente=" + idCliente + ", nombre=" + nombre + ", email=" + email + "]\n";
	}
	
}
