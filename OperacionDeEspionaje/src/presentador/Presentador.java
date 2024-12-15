package presentador;

import java.util.ArrayList;

import interfazVisual.AristaVista;
import interfazVisual.GrafoVista;
import interfazVisual.VerticeVista;
import modelo.Grafo;


public class Presentador {
	private GrafoVista grafoVista;
    private Grafo modelo;
    private Grafo AGM;
    

    public void solicitarInicializacionDeGrafo(int vertices) {
        this.modelo = new Grafo(vertices); 
    }
    
    public void conversionGrafoVistaAGrafo(GrafoVista grafoVista) {

        for (AristaVista aristaVista : grafoVista.getAristas()) {
            VerticeVista vertice1 = aristaVista.getOrigen();
            VerticeVista vertice2 = aristaVista.getDestino();
            double peso = aristaVista.getPesoArista();

            int indice1 = vertice1.getId(); 
            int indice2 = vertice2.getId();

            modelo.agregarArista(indice1, indice2, peso); 
        }
    }
    
    public boolean grafoEsContiguo() {
    	return modelo.esContiguo();
    }
    
    
    public void conversionGrafoAGrafoVista() {
        grafoVista = new GrafoVista(AGM.tamano());
        ArrayList<VerticeVista> verticesVista = new ArrayList<>();

        int radio = 200;  
        int centroX = 250; 
        int centroY = 250;
        int numVertices = AGM.tamano();

        for (int i = 0; i < numVertices; i++) {

            int x = (int) (centroX + radio * Math.cos(2 * Math.PI * i / numVertices));
            int y = (int) (centroY + radio * Math.sin(2 * Math.PI * i / numVertices));

            VerticeVista vertice = new VerticeVista(i, x, y);  
            verticesVista.add(vertice);  
        }

        grafoVista.setVertices(verticesVista);  

        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
            	
                if (AGM.existeArista(i, j)) {
                	
                    double peso = AGM.getPesoArista(i, j);
                    VerticeVista vertice1 = verticesVista.get(i);
                    VerticeVista vertice2 = verticesVista.get(j);
                    grafoVista.agregarArista(vertice1, vertice2, peso);
                }
            }
        }
    }


    public void solicitarGeneracionArbolMinimo() {
        AGM = modelo.GenerarArbolGeneradorMinimoPrim();
    }
    

    public Grafo solicitarGrafo() {
    	return this.modelo;
    }
    

	public GrafoVista getGrafoVista() {
		return grafoVista;
	}


	public void setGrafoVista(GrafoVista grafoVista) {
		this.grafoVista = grafoVista;
	}


	public Grafo getModelo() {
		return modelo;
	}


	public void setModelo(Grafo modelo) {
		this.modelo = modelo;
	}


	public Grafo getAGM() {
		return AGM;
	}


	public void setAGM(Grafo aGM) {
		AGM = aGM;
	}
}
