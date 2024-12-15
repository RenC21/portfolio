package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import interfazVisual.VerticeVista;
import interfazVisual.AristaVista;

public class AristaVistaTest {

    @Test
    public void testCrearArista() {
        VerticeVista origen = new VerticeVista(1, 100, 200);
        VerticeVista destino = new VerticeVista(2, 300, 400);
        AristaVista arista = new AristaVista(origen, destino, 2.5);

        assertEquals(origen, arista.getOrigen());
        assertEquals(destino, arista.getDestino());
        assertEquals(2.5, arista.getPesoArista(), 0.001);
    }

    @Test
    public void testModificarPesoArista() {
        VerticeVista origen = new VerticeVista(1, 100, 200);
        VerticeVista destino = new VerticeVista(2, 300, 400);
        AristaVista arista = new AristaVista(origen, destino, 1.0);

        arista.setPesoArista(3.0);

        assertEquals(3.0, arista.getPesoArista(), 0.001);
    }
}
