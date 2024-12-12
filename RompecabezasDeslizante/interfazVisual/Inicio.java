package interfazVisual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Inicio extends JFrame {

	//Constructor de Inicio (menu de inicio)
    public Inicio() {
        // Configuración básica de la ventana
        setTitle("Rompecabezas deslizante");
        setSize(525, 535); // Tamaño de la ventana (ancho x alto)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierre de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        
        // Se carga el GIF 
        String workingDir = System.getProperty("user.dir");
        String gifPath = workingDir + "//fondoInicio.gif";
        JLabel backgroundLabel = new JLabel(new ImageIcon(gifPath));
        backgroundLabel.setLayout(new GridBagLayout()); 

        // Añadir el JLabel del fondo al JFrame
        setContentPane(backgroundLabel); 

        GridBagConstraints gbc = new GridBagConstraints();
        // Se definen margenes entre los componentes
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Panel transparente para agregar los componentes encima del fondo GIF
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false); // Hace el panel transparente para que se vea el GIF

        // Configuración título
        JLabel labelTitulo = crearLabel("¡ROMPECABEZAS DESLIZANTE!", new Font("Arial", Font.BOLD, 30), new Color(92, 84, 112));
        labelTitulo.setOpaque(true); 
        labelTitulo.setBackground(new Color(185, 180, 199)); 
        labelTitulo.setPreferredSize(new Dimension(480, 60));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER); 
        labelTitulo.setVerticalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0; 
        contentPanel.add(labelTitulo, gbc);


        // Configuración para texto informativo
        JLabel textoInformativo = crearLabel("Ingrese su nombre y elija la dificultad del juego:", new Font("Arial", Font.BOLD, 18), Color.WHITE);
        textoInformativo.setOpaque(true); 
        textoInformativo.setBackground(new Color(92, 84, 112)); 
        gbc.gridy = 1; 
        contentPanel.add(textoInformativo, gbc);
        
        // Configuracion mensaje indicativo
        JLabel mensajeNombre = crearLabel(" Ingrese su nombre: ", new Font("Arial", Font.BOLD, 15), new Color(92, 84, 112));
        mensajeNombre.setOpaque(true); 
        gbc.gridy = 2; 
        contentPanel.add(mensajeNombre, gbc);
        
        // Configuracion para campo para ingresar nombre de jugador
        JTextField campoNombre = new JTextField();
        campoNombre.setPreferredSize(new Dimension(110, 20)); 
        campoNombre.setBackground(new Color(185, 180, 199));
        gbc.gridy = 3; 
        contentPanel.add(campoNombre, gbc);

        // Configuracion de grupo de botones de opción para dificultad
        JRadioButton facil = new JRadioButton("Fácil: tablero 3x3 con 100 movimientos", true);
        JRadioButton medio = new JRadioButton("Medio: tablero 4x4 con 50 movimientos");
        JRadioButton dificil = new JRadioButton("Difícil: tablero 5x5 con 5 movimientos");

        configurarBotonOpcion(facil);
        configurarBotonOpcion(medio);
        configurarBotonOpcion(dificil);
        
        ButtonGroup grupoDificultad = new ButtonGroup();
        grupoDificultad.add(facil);
        grupoDificultad.add(medio);
        grupoDificultad.add(dificil);

        JPanel panelDificultad = new JPanel(new GridLayout(3, 1, 5, 5)); 
        panelDificultad.setOpaque(false);
        panelDificultad.add(facil);
        panelDificultad.add(medio);
        panelDificultad.add(dificil);
        gbc.gridy = 4; 
        contentPanel.add(panelDificultad, gbc);

        // Configuración botón iniciar juego
        JButton botonInicio = crearBoton("¡A JUGAR!", new Font("Arial", Font.BOLD, 24), new Color (92, 84, 112), new Color(185, 180, 199));
        botonInicio.setOpaque(true); 
        botonInicio.setBackground(new Color(185, 180, 199)); 
        gbc.gridy = 5; 
        contentPanel.add(botonInicio, gbc);

        // Acción del botón de inicio (INICIAR JUEGO)
        botonInicio.addActionListener((ActionEvent e) -> {
            int dimension = 0;
            int cantidadMovimientos = 0;
            int cantidadVecesADesordenarTablero = 0;
            
            //Capturo nombre ingresado por jugador
            String nombreJugador = campoNombre.getText();
            
            //Capturo valor de dificultad de juego ingresada por jugador
            if (facil.isSelected()) {
                dimension = 3;
                cantidadMovimientos = 100;
                cantidadVecesADesordenarTablero = 50;
            }
            if (medio.isSelected()) {
                dimension = 4;
                cantidadMovimientos = 170;
                cantidadVecesADesordenarTablero = 100;
            }
            if (dificil.isSelected()) {
                dimension = 5;
                cantidadMovimientos = 250;
                cantidadVecesADesordenarTablero = 200;
            }
            
            // Se cierra la ventana actual
            dispose(); 
            
            //Se inicializa ventana con juego
            new Ventana(dimension, cantidadMovimientos, nombreJugador, cantidadVecesADesordenarTablero);
        });
        
        add(contentPanel);
    }

    // Método para configurar los JRadioButton
    private void configurarBotonOpcion(JRadioButton boton) {
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setBackground(new Color(53, 47, 68)); // Fondo transparente
        boton.setForeground(Color.WHITE); // Color de texto
    }

    // Método para crear un JLabel 
    private JLabel crearLabel(String texto, Font fuente, Color color) {
        JLabel label = new JLabel(texto);
        label.setFont(fuente);
        label.setForeground(color);
        return label;
    }

    // Método para crear un JButton 
    private JButton crearBoton(String texto, Font fuente, Color fg, Color bg) {
        JButton boton = new JButton(texto);
        boton.setFont(fuente);
        boton.setForeground(fg);
        boton.setBackground(bg);
        boton.setOpaque(true);
        boton.setBorderPainted(false);
        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inicio ventanaInicio = new Inicio();
            ventanaInicio.setVisible(true);
        });
    }
}


