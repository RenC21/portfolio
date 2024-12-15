package presentador;
import java.util.ArrayList;

import interfazVisual.Objetos.OfertaVista;
import modelo.Modelo;
import modelo.Objetos.Oferta;



public class Presentador {
	private Modelo modelo;
	
	public Presentador() {
		this.modelo = new Modelo();
	}
	
	
	public void agregarOfertaArchivo (OfertaVista ofertaVista) {
		Oferta nuevaOferta = conversionOfertaVistaAOferta (ofertaVista);
		modelo.agregarOferta(nuevaOferta);
	}
	
	public ArrayList<Oferta> obtenerOfertasArchivo(){
		return modelo.deserializarOfertas();
	}
	
	public ArrayList<Oferta> obtenerOfertasRegistradas(){
		ArrayList<Oferta> listaOfertas = modelo.obtenerOfertasRegistradas();
		return listaOfertas;
	}
	
	
	public ArrayList<OfertaVista> conversionDeOfertas() {
	    ArrayList<Oferta> listaOfertas = obtenerOfertasRegistradas();
	    ArrayList<OfertaVista> ofertasParaVista = new ArrayList<>();

	    if (listaOfertas != null && !listaOfertas.isEmpty()) { // Verifica si listaOfertas no es null y no está vacía
	        for (Oferta oferta : listaOfertas) {
	            OfertaVista ofertaVista = new OfertaVista(
	                    oferta.getMonto(),
	                    oferta.getHoraInicio(),
	                    oferta.getHoraFinal(),
	                    oferta.getOfertante());

	            ofertasParaVista.add(ofertaVista);
	        }
	    }
	    return ofertasParaVista;
	}

	
	public Oferta conversionOfertaVistaAOferta (OfertaVista ofertaVista) {
		Oferta oferta = new Oferta(
				ofertaVista.getMonto(), 
				ofertaVista.getHoraInicio(), 
				ofertaVista.getHoraFinal(), 
				ofertaVista.getOfertante());
		
		return oferta;
	}
	

	public OfertaVista conversionOfertaAOfertaVista (Oferta oferta) {
		OfertaVista ofertaVista = new OfertaVista(
				oferta.getMonto(), 
				oferta.getHoraInicio(), 
				oferta.getHoraFinal(), 
				oferta.getOfertante());
		
		return ofertaVista;
	}
	
	public void calcularMejoresOfertas(){
		modelo.calcularMejoresOfertas();	
	}
	
	public void borrarContenidoArchivo() {
		modelo.vaciarArchivo();
	}
	
	public boolean verificarArchivoVacio() {
		return modelo.verificarArchivoVacio();
	}
}
