package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import interfazVisual.VerticeVista;

public class VerticeVistaTest {

    @Test
    public void testCrearVertice() {
        VerticeVista vertice = new VerticeVista(1, 100, 200);

        assertEquals(1, vertice.getId());
        assertEquals(100, vertice.getX());
        assertEquals(200, vertice.getY());
        assertNull(vertice.getNombreEspia()); // Verificamos que el nombre del espía es nulo al crear
    }

    @Test
    public void testAsignarNombreEspia() {
        VerticeVista vertice = new VerticeVista(2, 150, 250);
        vertice.setNombreEspia("Espía 007");

        assertEquals("Espía 007", vertice.getNombreEspia()); // Verificamos que se asigna correctamente el nombre
    }

    @Test
    public void testModificarCoordenadas() {
        VerticeVista vertice = new VerticeVista(3, 0, 0);
        vertice.setX(300);
        vertice.setY(400);

        assertEquals(300, vertice.getX());
        assertEquals(400, vertice.getY());
    }
}
