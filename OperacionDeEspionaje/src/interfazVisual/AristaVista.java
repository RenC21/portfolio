package interfazVisual;

public class AristaVista {
	private VerticeVista origen;
	private VerticeVista destino;
	private double pesoArista;

	public AristaVista(VerticeVista origen, VerticeVista destino, double peso) {
		this.origen = origen;
		this.destino= destino;
		this.pesoArista = peso;
	}

	public VerticeVista getOrigen() {
		return origen;
	}

	public void setOrigen(VerticeVista origen) {
		this.origen = origen;
	}

	public VerticeVista getDestino() {
		return destino;
	}

	public void setDestino(VerticeVista destino) {
		this.destino = destino;
	}

	public double getPesoArista() {
		return pesoArista;
	}

	public void setPesoArista(double pesoArista) {
		this.pesoArista = pesoArista;
	}
}
