package interfazVisual;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class VistaFinJuego {
	
	private String mensaje;
	private String[] opciones;
	private Ventana ventana;

	//Constructor de VistaFinJuego
	public VistaFinJuego(String mensaje, String[] opciones, Ventana ventana) {
		this.mensaje = mensaje;
		this.opciones = opciones;
		this.ventana = ventana;
	}
	
	//Metodo para mostrar casilla de mensaje
    public void mostrarMensaje() {
   
        int seleccion = JOptionPane.showOptionDialog(
            null, 
            this.mensaje, 
            "Rompecabezas deslizante - fin del juego", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            null, 
            opciones, 
            opciones[0]
        );

        //Si el jugador decide salir del juego
        if (seleccion == 1) {
            System.exit(0);
            
        //Si el jugador decide continuar jugando
        } else if (seleccion == 0) {
        	ventana.dispose();
        	
            // Se muestra la ventana de inicio nuevamente
            SwingUtilities.invokeLater(() -> {
                Inicio ventanaInicio = new Inicio();
                ventanaInicio.setVisible(true);
            });   
        }
    }
}
