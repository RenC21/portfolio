package interfazVisual;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;

public class Ventana extends JFrame {
    public static Container contenedor;
    public Juego juego; 

    //Constructor de Ventana
    public Ventana(int dimension, int cantidadMovimientos, String nombreJugador, int cantidadVecesADesordenarTablero) {
    	
        // Configuración básica de la ventana
        setTitle("Rompecabeza deslizante");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        contenedor = getContentPane();

        // Instancia un nuevo juego con tablero
        juego = new Juego(this, dimension, 500, cantidadMovimientos, nombreJugador, cantidadVecesADesordenarTablero);

        // Agrega juego al contenedor de la ventana
        contenedor.add(juego, BorderLayout.CENTER);

        setVisible(true);
    }
}
