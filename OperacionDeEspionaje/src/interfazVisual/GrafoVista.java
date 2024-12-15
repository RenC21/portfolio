package interfazVisual;

import java.util.ArrayList;


public class GrafoVista {
	private ArrayList<VerticeVista> vertices;
	private ArrayList<AristaVista> aristas;
	private int tamanoGrafo;

	public GrafoVista(int tamanoGrafo) {

		this.vertices = new ArrayList<>();
		this.aristas = new ArrayList<>();
		this.tamanoGrafo = tamanoGrafo;

	}
	
	public void configurarPosicionesGrafo() {
		int posX=30;
		int posY=80;
		int flag = 1;

		for(int i = 0; i < tamanoGrafo; i++) {


			if(i!=0) {
				if(i%3 == 0) {
					posX=posX+200;
					posY=posY-400;
				}else {
					posY=posY+200;
				}
			}

			if(flag>0) {
				flag--;
				vertices.add(new VerticeVista(i,posX+100,posY));
			}else {
				vertices.add(new VerticeVista(i,posX,posY));
				flag=2;
			}	
		}	
	}
	
	public void agregarArista(VerticeVista vertice1, VerticeVista vertice2, double peso) {
		this.aristas.add(new AristaVista(vertice1,vertice2,peso));
	}

	public int getTamanoGrafo() {
		return tamanoGrafo;
	}

	public void setTamanoGrafo(int tamanoGrafo) {
		this.tamanoGrafo = tamanoGrafo;
	}

	public ArrayList<VerticeVista> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<VerticeVista> vertices) {
		this.vertices = vertices;
	}
	
	public VerticeVista obtenerVerticePorNombre(String nombreEspia) {
        for (VerticeVista vertice : vertices) {
            if (vertice.getNombreEspia().equals(nombreEspia)) {
                return vertice; 
            }
        }
        return null; 
    }

	public ArrayList<AristaVista> getAristas() {
		return aristas;
	}

	public void setAristas(ArrayList<AristaVista> aristas) {
		this.aristas = aristas;
	}
}
