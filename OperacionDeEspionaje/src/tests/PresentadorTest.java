package tests;

import interfazVisual.AristaVista;
import interfazVisual.GrafoVista;
import interfazVisual.VerticeVista;
import modelo.Grafo;
import org.junit.Before;
import org.junit.Test;
import presentador.Presentador;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class PresentadorTest {

    private Presentador presentador;
    private GrafoVista grafoVista;

    @Before
    public void setUp() {
        presentador = new Presentador();
        
        // Configurar un GrafoVista para pruebas
        grafoVista = new GrafoVista(3); // Grafo con 3 vértices
        VerticeVista vertice1 = new VerticeVista(0, 100, 100);
        VerticeVista vertice2 = new VerticeVista(1, 200, 200);
        VerticeVista vertice3 = new VerticeVista(2, 300, 300);

        grafoVista.setVertices(new ArrayList<VerticeVista>() {{
            add(vertice1);
            add(vertice2);
            add(vertice3);
        }});

        grafoVista.agregarArista(vertice1, vertice2, 1.5);
        grafoVista.agregarArista(vertice2, vertice3, 2.5);
    }

    @Test
    public void testSolicitarInicializacionDeGrafo() {
        presentador.solicitarInicializacionDeGrafo(4);
        Grafo grafo = presentador.solicitarGrafo();

        assertNotNull("El grafo debe estar inicializado", grafo);
        assertEquals("El grafo debe tener 4 vértices", 4, grafo.tamano());
    }

    @Test
    public void testConversionGrafoVistaAGrafo() {
        presentador.solicitarInicializacionDeGrafo(3);
        presentador.conversionGrafoVistaAGrafo(grafoVista);
        Grafo modelo = presentador.solicitarGrafo();

        assertTrue("El grafo debe tener la arista (0,1) con peso 1.5", modelo.existeArista(0, 1));
        assertEquals("El peso de la arista (0,1) debe ser 1.5", 1.5, modelo.getPesoArista(0, 1), 0.01);
        assertTrue("El grafo debe tener la arista (1,2) con peso 2.5", modelo.existeArista(1, 2));
        assertEquals("El peso de la arista (1,2) debe ser 2.5", 2.5, modelo.getPesoArista(1, 2), 0.01);
    }

    @Test
    public void testGrafoEsContiguo() {
        presentador.solicitarInicializacionDeGrafo(3);
        presentador.conversionGrafoVistaAGrafo(grafoVista);

        assertTrue("El grafo debe ser contiguo", presentador.grafoEsContiguo());
    }

    @Test
    public void testConversionGrafoAGrafoVista() {
        presentador.solicitarInicializacionDeGrafo(3);
        presentador.conversionGrafoVistaAGrafo(grafoVista);
        presentador.solicitarGeneracionArbolMinimo();
        presentador.conversionGrafoAGrafoVista();

        GrafoVista grafoVistaResultante = presentador.getGrafoVista();
        assertNotNull("El GrafoVista resultante debe estar inicializado", grafoVistaResultante);
        assertEquals("El GrafoVista debe tener 3 vértices", 3, grafoVistaResultante.getVertices().size());

        ArrayList<AristaVista> aristas = grafoVistaResultante.getAristas();
        assertNotNull("El GrafoVista debe tener aristas", aristas);
    }

    @Test
    public void testSolicitarGeneracionArbolMinimo() {
        presentador.solicitarInicializacionDeGrafo(3);
        presentador.conversionGrafoVistaAGrafo(grafoVista);
        presentador.solicitarGeneracionArbolMinimo();

        Grafo agm = presentador.getAGM();
        assertNotNull("El AGM debe estar generado", agm);
        assertTrue("El AGM debe tener la arista (0,1)", agm.existeArista(0, 1));
        assertTrue("El AGM debe tener la arista (1,2)", agm.existeArista(1, 2));
    }
}
