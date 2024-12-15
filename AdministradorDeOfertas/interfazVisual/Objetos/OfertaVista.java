package interfazVisual.Objetos;

public class OfertaVista 
{
	private double monto;
	private String ofertante;
	private int horaInicio;
	private int horaFinal;
	
	public OfertaVista (double monto, int horaInicio, int horaFinal, String ofertante) {
		this.monto=monto;
		this.ofertante=ofertante;
		this.horaInicio=horaInicio;
		this.horaFinal=horaFinal;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	public int getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(int horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getOfertante() {
		return ofertante;
	}

	public void setOfertante(String ofertante) {
		this.ofertante = ofertante;
	}

	@Override
	public String toString() {
		return "Oferta [monto=" + monto + ", ofertante=" + ofertante + ", horaInicio=" + horaInicio
				+ ", horaFinal=" + horaFinal + "]";
	}
}
