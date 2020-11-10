package integrador.clases;

public class FacturaDTO {
	private int idFactura;
	private int idCliente;
	
	public FacturaDTO(int idFactura, int idCliente) {
		super();
		this.idFactura = idFactura;
		this.idCliente = idCliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public int getIdFactura() {
		return idFactura;
	}

	@Override
	public String toString() {
		return "[idFactura=" + idFactura + ", idCliente=" + idCliente + "]";
	}
}
