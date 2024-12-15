package interfazVisual;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import interfazVisual.Objetos.OfertaVista;
import presentador.Presentador;

import java.awt.Font;
import java.util.ArrayList;


public class VentanaInicio {

    private JFrame frame;
    private JTextField txtOfertante;
    private JTextField txtMonto;
    private JTextField txtHoraInicio;
    private JTextField txtHoraFin;
    private JTable table;
    private Presentador presentador;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaInicio window = new VentanaInicio();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaInicio() {
        initialize();
        presentador = new Presentador();
        Color fondoPanel = new Color(192, 192, 192); 
        Color colorBoton = new Color(128, 128, 128); 
        Color colorTextoBoton = Color.WHITE;
        
        frame.getContentPane().setBackground(Color.WHITE);

        JPanel panelIngreso = new JPanel();
        panelIngreso.setBounds(10, 10, 250, 300);
        panelIngreso.setLayout(null);
        panelIngreso.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingrese una oferta"));
        panelIngreso.setBackground(fondoPanel);
        frame.getContentPane().add(panelIngreso);

        JLabel lblOfertante = new JLabel("Ofertante:");
        lblOfertante.setBounds(10, 30, 100, 20);
        lblOfertante.setForeground(Color.BLACK);
        panelIngreso.add(lblOfertante);

        txtOfertante = new JTextField();
        txtOfertante.setBounds(10, 50, 220, 25);
        txtOfertante.setBackground(Color.WHITE);
        panelIngreso.add(txtOfertante);
        txtOfertante.setColumns(10);

        JLabel lblMonto = new JLabel("Monto:");
        lblMonto.setBounds(10, 80, 100, 20);
        lblMonto.setForeground(Color.BLACK);
        panelIngreso.add(lblMonto);

        txtMonto = new JTextField();
        txtMonto.setBounds(10, 100, 220, 25);
        txtMonto.setBackground(Color.WHITE);
        panelIngreso.add(txtMonto);
        txtMonto.setColumns(10);

        JLabel lblHoraInicio = new JLabel("Hora inicio:");
        lblHoraInicio.setBounds(10, 130, 100, 20);
        lblHoraInicio.setForeground(Color.BLACK);
        panelIngreso.add(lblHoraInicio);

        txtHoraInicio = new JTextField();
        txtHoraInicio.setBounds(10, 150, 220, 25);
        txtHoraInicio.setBackground(Color.WHITE);
        panelIngreso.add(txtHoraInicio);
        txtHoraInicio.setColumns(10);

        JLabel lblHoraFin = new JLabel("Hora fin:");
        lblHoraFin.setBounds(10, 180, 100, 20);
        lblHoraFin.setForeground(Color.BLACK);
        panelIngreso.add(lblHoraFin);

        txtHoraFin = new JTextField();
        txtHoraFin.setBounds(10, 200, 220, 25);
        txtHoraFin.setBackground(Color.WHITE);
        panelIngreso.add(txtHoraFin);
        txtHoraFin.setColumns(10);

        JButton btnCargar = new JButton("Cargar");
        btnCargar.setBounds(30, 240, 80, 25);
        btnCargar.setBackground(colorBoton);
        btnCargar.setForeground(colorTextoBoton);
        btnCargar.setFocusPainted(false);
        panelIngreso.add(btnCargar);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(130, 240, 100, 25);
        btnCalcular.setBackground(colorBoton);
        btnCalcular.setForeground(colorTextoBoton);
        btnCalcular.setFocusPainted(false);
        panelIngreso.add(btnCalcular);

        JLabel lblOfertasIngresadas = new JLabel("Ofertas ingresadas");
        lblOfertasIngresadas.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblOfertasIngresadas.setForeground(Color.BLACK);
        lblOfertasIngresadas.setBounds(270, 10, 200, 20);
        frame.getContentPane().add(lblOfertasIngresadas);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(270, 40, 500, 270);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Ofertante", "Monto", "Hora inicio", "Hora fin"
            }
        ));
        table.setBackground(Color.WHITE); 
        table.setGridColor(Color.LIGHT_GRAY); 
        scrollPane.setViewportView(table);

        JButton btnRefrescar = new JButton("Refrescar");
        btnRefrescar.setBounds(670, 320, 100, 25);
        btnRefrescar.setBackground(colorBoton);
        btnRefrescar.setForeground(colorTextoBoton);
        btnRefrescar.setFocusPainted(false);
        frame.getContentPane().add(btnRefrescar);
    
        actualizarTablero();
        
        btnCargar.addActionListener(e -> {
        	try {
        		boolean camposCompletos = false;
            	camposCompletos = camposCompletos || (!txtOfertante.getText().isBlank()&& 
            											!txtMonto.getText().isBlank() && 
            											!txtHoraInicio.getText().isBlank() &&
            											!txtHoraFin.getText().isBlank());
            	
            	if (camposCompletos) {
            		String ofertante = txtOfertante.getText();
                	String monto = txtMonto.getText();
                	String horaInicio = txtHoraInicio.getText();
                	String horaFin = txtHoraFin.getText();

                	double montoValor = Double.parseDouble(monto); 
                	int horaInicioValor = Integer.parseInt(horaInicio);
                	int horaFinValor = Integer.parseInt(horaFin);
                	
                	boolean camposValidos = false;
                	camposValidos = camposValidos || (montoValor != 0 && 
            											horaInicioValor >= 0 && horaInicioValor <= 22 &&
            											horaFinValor >= 1 && horaFinValor <= 23 &&
            											horaInicioValor < horaFinValor);
                	
                	if (camposValidos) {
                		OfertaVista nuevaOferta = new OfertaVista(montoValor, horaInicioValor, horaFinValor, ofertante);
                    	presentador.agregarOfertaArchivo(nuevaOferta);
                    	actualizarTablero();
                    	resetearValoresInputs();
                	}
                	else {
                		JOptionPane.showMessageDialog(null, "Por favor, ingrese un monto y un horario de reserva dentro de lo establecido", "Error", JOptionPane.ERROR_MESSAGE);
                	}
            	}
            	else {
            		JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos antes de cargar la oferta.", "Warning", JOptionPane.WARNING_MESSAGE);
            	}
        	}
        	catch(Exception ex ) {
        		throw new RuntimeException("Fallo al cargar una nueva oferta.");
        	}
        });
         
      
        btnCalcular.addActionListener(e -> {
        	try {
        		if (presentador.verificarArchivoVacio()) {
        			JOptionPane.showMessageDialog(null, "No existen ofertas a calcular.", "Error", JOptionPane.ERROR_MESSAGE);
        		}
        		else {
            		presentador.calcularMejoresOfertas();
                	actualizarTablero();
                	resetearValoresInputs();
        		}
        	}
        	catch(Exception ex ) {
        		throw new RuntimeException("Fallo al calcular ofertas.");
        	}
        	
        });
        
        btnRefrescar.addActionListener(e -> {
        	try {
            	presentador.borrarContenidoArchivo();
            	actualizarTablero();
            	resetearValoresInputs();
        	}
        	catch(Exception ex ) {
        		throw new RuntimeException("Fallo al refrescar tablero");
        	}
        });   
    }


    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("Gestor de ofertas");
    }
    
    
    public void actualizarTablero() {
    	ArrayList<OfertaVista> ofertas = presentador.conversionDeOfertas();
    	DefaultTableModel tablero = (DefaultTableModel) table.getModel();
    	
    	if (ofertas.size() == 0) {
    		tablero.setRowCount(0);
    	}
    	else {
    		tablero.setRowCount(0);
        	for (OfertaVista oferta : ofertas) {
        		tablero.addRow(new Object[] {
        				oferta.getOfertante(), 
        				oferta.getMonto(), 
        				oferta.getHoraInicio(),
        				oferta.getHoraFinal()});
        	}
    	}
    }
    
    public void resetearValoresInputs() {
    	txtOfertante.setText("");
    	txtMonto.setText("");
    	txtHoraInicio.setText("");
    	txtHoraFin.setText("");
    }
}

