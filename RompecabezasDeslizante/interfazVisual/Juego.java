package interfazVisual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.FlowLayout;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import presentador.Presentador;
import java.awt.Dimension;

public class Juego extends JPanel {
    private Pieza[][] tableroCompleto;
    private Pieza[][] tableroDesordenado;
    private int dimension;
    private Presentador presentador;
    private Ventana Ventana;
    private JPanel interfazDelJuego;
    public JLabel movimientosRestantesLabel; 
    public JLabel tiempoTranscurridoLabel;
    public Timer timer;
    private int segundosTranscurridos;
    private String nombreJugador;

    //Constructor de Juego
    public Juego(Ventana ventana, int dimension, int tamanio, int cantidadMovimientos, String nombreJugador, int cantidadVecesADesordenarTablero) {
        this.dimension = dimension;
        this.tableroCompleto = new Pieza[dimension][dimension];
        this.tableroDesordenado = new Pieza[dimension][dimension];
        this.setBackground(Color.black);
        this.Ventana = ventana;
        int margen = 20;
        int tamanioReducido = tamanio - margen * 2;
        this.setPreferredSize(new Dimension(tamanioReducido, tamanioReducido));
        this.presentador = new Presentador(cantidadMovimientos, dimension, tamanio, nombreJugador);
        this.nombreJugador = nombreJugador;
        this.setLayout(new GridLayout(dimension, dimension));
        
        //Creo y desordeno tablero de juego
        this.tableroCompleto = presentador.solicitarTablero();
        this.tableroDesordenado = presentador.desordenarTablero(cantidadVecesADesordenarTablero);

        JPanel panelLabels = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20)); // 50 es la distancia horizontal entre labels
		interfazDelJuego = new JPanel(new BorderLayout());
        
        //Creo label para mostrar cantidad de movimientos restantes
        movimientosRestantesLabel = new JLabel("Movimientos restantes: " + obtenerCantidadMovimientos());
        ventana.add(movimientosRestantesLabel, BorderLayout.SOUTH);
        movimientosRestantesLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        
        //Creo label para mostrar el tiempo transcurrido
        tiempoTranscurridoLabel = new JLabel("Tiempo transcurrido: 0 segundos");
        tiempoTranscurridoLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelLabels.add(movimientosRestantesLabel,BorderLayout.WEST);
		panelLabels.add(tiempoTranscurridoLabel,BorderLayout.EAST);
		panelLabels.setBackground(new Color (200, 162, 200));
		panelLabels.setOpaque(true);
		interfazDelJuego.add(panelLabels,BorderLayout.SOUTH);
        ventana.add(interfazDelJuego, BorderLayout.SOUTH);

        //Inicializo el atributo de segundos transcurridos del juego en 0
        this.segundosTranscurridos = 0;
        
        //Inicializo eventos
        iniciarTimer();
        Borrar();
        inicializarEventos();
    }

    
    // METODOS //
    
    //Metodo para iniciar timer
    private void iniciarTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundosTranscurridos++;
                tiempoTranscurridoLabel.setText("Tiempo transcurrido: " + segundosTranscurridos + " segundos");
            }
        });
        timer.start();
    }

    //Metodo para remover contenido de ventana principal
    private void Borrar() {
        SwingUtilities.invokeLater(() -> {
            this.removeAll();
            Actualizar();
        });
    }

    //Metodo para actualizar y redibujar tablero (actualizado por modelo)
    private void Actualizar() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (this.tableroDesordenado[i][j] != null) {
                    this.add(tableroDesordenado[i][j]);
                }
            }
        }
        this.revalidate();
        this.repaint();
    }

    //Metodo para reasignarle a cada pieza del tablero un nuevo ActionListener
    private void inicializarEventos() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (this.tableroDesordenado[i][j] != null) {
                    tableroDesordenado[i][j].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            onPiezaClick(e);
                        }
                    });
                }
            }
        }
    }

    //Metodo que atiende al evento Click de cada pieza (al intentar moverla)
    private void onPiezaClick(ActionEvent e) {
        Pieza pieza = (Pieza) e.getSource();
        Point pActual = new Point(pieza.getxActual(), pieza.getyActual());

        //Valido si la pieza sobre la que se hizo Click, se puede mover (cercania con pieza vacia)
        this.tableroDesordenado = presentador.intentarMoverPieza(pActual);

        //Se corrobora si el jugador ya acomodo todas las piezas a su ubicacion correcta sin agotar sus movimientos restantes
        if (presentador.comprobarGano()) {
        	
        	//Detengo timer
            timer.stop();

            //Obtengo path del archivo txt para registrar nombre y puntaje del jugador
            String workingDir = System.getProperty("user.dir");
            String filePath = workingDir + "/puntitos.txt";
            
            //Registro en el archivo los segundos transcurridos del juego (puntaje del jugador)
            //Se registrara el jugador/puntaje pero si el puntaje obtenido es inferior al de los primeros 5, no formara parte del listado
            presentador.registrarPuntajeGanador(filePath, segundosTranscurridos);
            
            //Se obtiene listado de jugadores ordenado (de menor puntaje a mayor)
            List<String> listaRanking = presentador.obtenerRankingCompleto(filePath);

            //Se define un salto de linea por cada linea
            String ranking = String.join("\n", listaRanking);

            //Se instancia casilla de mensaje para mostrar mensaje ganador + ranking 
            new VistaFinJuego(
                "¡Felicitaciones " + nombreJugador + "!\n" + "¡Ganaste en " + segundosTranscurridos + " segundos!\n\n" + "5 mejores resultados:\n\n" + ranking + "\n\n",
                new String[] {"Jugar de nuevo", "Salir"}, 
                Ventana
            ).mostrarMensaje();
        }

        //Se corrobora si el usuario se quedo sin movimientos y no logro acomodar todas las piezas
        if (presentador.comprobarPerdio()) {
            timer.stop();
            new VistaFinJuego("¡Perdiste! ¡Volve a intentarlo y ocupa tu lugar en el ranking!", new String[] {"Jugar de nuevo", "Salir"}, Ventana).mostrarMensaje();
        }

        //Se restan movimientos disponibles por cada pieza correcta que se intenta mover
        if (presentador.piezaSeMovio()) {
            this.presentador.restarMovimiento();
        }

        //Actualizo valor de cantidad de movimientos restantes 
        movimientosRestantesLabel.setText("Movimientos restantes: " + obtenerCantidadMovimientos());
        inicializarEventos();
        Borrar();
    }

    
    //Metodo para obtener la cantidad de movimientos restantes que tiene el jugador
    public int obtenerCantidadMovimientos() {
        return presentador.solicitarCantidadMovimientos();
    }
}

