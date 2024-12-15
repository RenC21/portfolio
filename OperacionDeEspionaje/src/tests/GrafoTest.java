package tests;

import modelo.Grafo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GrafoTest {

    private Grafo grafo;

    @Before
    public void setUp() {
        // Inicializar un grafo con 5 vértices para cada prueba
        grafo = new Grafo(5);
    }

    @Test
    public void testAgregarArista() {
        // Agregar una arista y verificar que exista
        grafo.agregarArista(0, 1, 0.5);
        assertTrue("La arista debe existir después de ser agregada", grafo.existeArista(0, 1));
        assertEquals("El peso de la arista debe ser 0.5", 0.5, grafo.getPesoArista(0, 1), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAgregarAristaLoop() {
        // Intentar agregar una arista a sí mismo debe lanzar una excepción
        grafo.agregarArista(0, 0, 1.0);
    }

    @Test
    public void testEliminarArista() {
        // Agregar una arista y luego eliminarla
        grafo.agregarArista(0, 1, 0.5);
        assertTrue("La arista debe existir después de ser agregada", grafo.existeArista(0, 1));

        grafo.eliminarArista(0, 1);
        assertFalse("La arista debe ser eliminada correctamente", grafo.existeArista(0, 1));
    }

    @Test
    public void testExisteArista() {
        // Verificar la existencia de aristas
        assertFalse("La arista no debe existir antes de ser agregada", grafo.existeArista(1, 2));
        grafo.agregarArista(1, 2, 1.2);
        assertTrue("La arista debe existir después de ser agregada", grafo.existeArista(1, 2));
    }

    @Test
    public void testGetPesoArista() {
        // Agregar una arista y verificar su peso
        grafo.agregarArista(2, 3, 2.0);
        assertEquals("El peso de la arista debe ser 2.0", 2.0, grafo.getPesoArista(2, 3), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAgregarAristaVerticesInvalidos() {
        // Intentar agregar una arista con vértices fuera de rango debe lanzar una excepción
        grafo.agregarArista(5, 6, 1.0);
    }

    @Test
    public void testGenerarArbolGeneradorMinimoPrim() {
        // Crear un grafo y agregar aristas para formar un AGM predecible
        grafo.agregarArista(0, 1, 1.0);
        grafo.agregarArista(1, 2, 2.0);
        grafo.agregarArista(0, 3, 3.0);
        grafo.agregarArista(3, 4, 4.0);
        grafo.agregarArista(1, 4, 5.0);

        Grafo arbolMinimo = grafo.GenerarArbolGeneradorMinimoPrim();

        // Verificar que el árbol generado tiene las aristas correctas
        assertTrue("La arista (0,1) debe estar en el AGM", arbolMinimo.existeArista(0, 1));
        assertTrue("La arista (1,2) debe estar en el AGM", arbolMinimo.existeArista(1, 2));
        assertTrue("La arista (0,3) debe estar en el AGM", arbolMinimo.existeArista(0, 3));
        assertTrue("La arista (3,4) debe estar en el AGM", arbolMinimo.existeArista(3, 4));
        assertFalse("La arista (1,4) no debe estar en el AGM", arbolMinimo.existeArista(1, 4));

        // El árbol debe tener exactamente 4 aristas (vértices - 1)
        int numeroAristas = 0;
        for (int i = 0; i < arbolMinimo.tamano(); i++) {
            for (int j = i + 1; j < arbolMinimo.tamano(); j++) {
                if (arbolMinimo.existeArista(i, j)) {
                    numeroAristas++;
                }
            }
        }
        assertEquals("El AGM debe tener 4 aristas", 4, numeroAristas);
    }
}
