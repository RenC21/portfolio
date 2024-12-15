package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import interfazVisual.VerticeVista;
import interfazVisual.GrafoVista;
import interfazVisual.AristaVista;

import java.util.ArrayList;

public class GrafoVistaTest {

    @Test
    public void testAgregarVerticesAlGrafo() {
        GrafoVista grafo = new GrafoVista(3);

        VerticeVista vertice1 = new VerticeVista(0, 100, 100);
        VerticeVista vertice2 = new VerticeVista(1, 200, 200);

        ArrayList<VerticeVista> vertices = new ArrayList<>();
        vertices.add(vertice1);
        vertices.add(vertice2);
        grafo.setVertices(vertices);

        assertEquals(2, grafo.getVertices().size());
        assertEquals(vertice1, grafo.getVertices().get(0));
        assertEquals(vertice2, grafo.getVertices().get(1));
    }

    @Test
    public void testAgregarAristasAlGrafo() {
        GrafoVista grafo = new GrafoVista(3);
        
        VerticeVista vertice1 = new VerticeVista(0, 100, 100);
        VerticeVista vertice2 = new VerticeVista(1, 200, 200);

        grafo.agregarArista(vertice1, vertice2, 1.5);

        assertEquals(1, grafo.getAristas().size());
        AristaVista arista = grafo.getAristas().get(0);
        assertEquals(1.5, arista.getPesoArista(), 0.001);
    }

    @Test
    public void testObtenerVerticePorNombreEspia() {
        GrafoVista grafo = new GrafoVista(2);

        VerticeVista vertice1 = new VerticeVista(0, 100, 100);
        vertice1.setNombreEspia("Espía A");

        VerticeVista vertice2 = new VerticeVista(1, 200, 200);
        vertice2.setNombreEspia("Espía B");

        ArrayList<VerticeVista> vertices = new ArrayList<>();
        vertices.add(vertice1);
        vertices.add(vertice2);
        grafo.setVertices(vertices);

        VerticeVista verticeEncontrado = grafo.obtenerVerticePorNombre("Espía A");
        assertEquals(vertice1, verticeEncontrado);
        
        VerticeVista verticeNoEncontrado = grafo.obtenerVerticePorNombre("Espía X");
        assertNull(verticeNoEncontrado);
    }
}

