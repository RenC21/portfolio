package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import modelo.Objetos.Oferta;

public class AlgoritmoGoloso {
	private ArrayList<Oferta> Ofertas;
	
	public AlgoritmoGoloso (ArrayList<Oferta> ofertas) {
		this.Ofertas = ofertas;
	}
	
	public ArrayList<Oferta> calcularMejoresOfertas() {
	    ArrayList<Oferta> ofertasOrdenadas = ordenarOfertas();
	    ArrayList<Oferta> ofertasAceptadas = new ArrayList<Oferta>();

	    for (Oferta oferta : ofertasOrdenadas) {
	        boolean horarioCompatible = true;

	        for (Oferta ofertaAceptada : ofertasAceptadas) {
	            if (!oferta.compaginarHorarios(ofertaAceptada)) {
	                horarioCompatible = false;
	                break; 
	            }
	        }
	        if (horarioCompatible) {
	            ofertasAceptadas.add(oferta);
	        }
	    }
	    return ofertasAceptadas;
	}
	
	public ArrayList<Oferta> ordenarOfertas(){
		ArrayList<Oferta> copiaOfertas = this.Ofertas;
		Collections.sort(copiaOfertas);
		Collections.reverse(copiaOfertas);
		for(Oferta o:copiaOfertas) {
		}
		return copiaOfertas;
	}	
}