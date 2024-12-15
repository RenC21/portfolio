package modelo.Objetos;

import java.io.Serializable;

public class Oferta implements Comparable, Serializable
{
	private static int contadorId = 0;
	private int id;
	private double monto;
	private String ofertante;
	private int horaInicio;
	private int horaFinal;
	private static final long serialVersionUID = 1L;
	
	public Oferta (double monto, int horaInicio, int horaFinal, String ofertante) {
		this.id = ++contadorId;
		this.monto=monto;
		this.ofertante=ofertante;
		this.horaInicio=horaInicio;
		this.horaFinal=horaFinal;
	}
	
	public boolean compaginarHorarios (Oferta o) {
		if (this.getHoraFinal() <= o.getHoraInicio() || o.getHoraFinal() <= this.getHoraInicio()) {
			return true;
		}
		return false;
	}
	
	public double calcularBeneficio() {
		return this.monto / (this.horaFinal - this.horaInicio);
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
	public int compareTo(Object o) {
		return (int) (this.calcularBeneficio() - ((Oferta) o).calcularBeneficio());
	}

	@Override
	public String toString() {
		return "Oferta [id=" + id + ", monto=" + monto + ", ofertante=" + ofertante + ", horaInicio=" + horaInicio
				+ ", horaFinal=" + horaFinal + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}