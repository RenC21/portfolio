package interfazVisual;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class IngresoNombresEspias {
	private JPanel panelIngresoNombresEspias;
	private JButton botonIngresoNombresEspias;
	private JLabel mensajeIngresoNombresEspias;
	private int cantidadEspias;
	private Map<Integer, JTextField> nombresEspias;
	private Image fondo;
	
	public IngresoNombresEspias(int cantidadEspias){
		
		this.cantidadEspias = cantidadEspias;
		this.nombresEspias = new HashMap<>();
		
		fondo = Toolkit.getDefaultToolkit().getImage("interfazVisual/images/mapa.jpg");
		
		panelIngresoNombresEspias = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };

        this.mensajeIngresoNombresEspias = new JLabel("Definir los nombres de los espias");
        mensajeIngresoNombresEspias.setFont(new Font("Garamond", Font.BOLD, 24));
        panelIngresoNombresEspias.add(mensajeIngresoNombresEspias);
        panelIngresoNombresEspias.setLayout(new BoxLayout(panelIngresoNombresEspias, BoxLayout.Y_AXIS));
        
        for (int i = 0; i < cantidadEspias; i++) {
        	
            JPanel espiaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            
            JLabel espiaLabel = new JLabel("Espía " + i);
            espiaPanel.add(espiaLabel);
            
            JTextField nombreEspia = new JTextField(10);
            espiaPanel.add(nombreEspia);
            
            nombresEspias.put(i, nombreEspia);

            panelIngresoNombresEspias.add(espiaPanel);
        }
        
        this.botonIngresoNombresEspias = new JButton("Siguiente");
        botonIngresoNombresEspias.setFont(new Font("Garamond", Font.BOLD, 18));
        panelIngresoNombresEspias.add(botonIngresoNombresEspias);
        panelIngresoNombresEspias.repaint();
	}
	

	public JPanel getPanelIngresoNombresEspias() {
		return panelIngresoNombresEspias;
	}

	public void setPanelIngresoNombresEspias(JPanel panelIngresoNombresEspias) {
		this.panelIngresoNombresEspias = panelIngresoNombresEspias;
	}

	public JButton getBotonIngresoNombresEspias() {
		return botonIngresoNombresEspias;
	}

	public void setBotonIngresoNombresEspias(JButton botonIngresoNombresEspias) {
		this.botonIngresoNombresEspias = botonIngresoNombresEspias;
	}

	public JLabel getMensajeIngresoNombresEspias() {
		return mensajeIngresoNombresEspias;
	}

	public void setMensajeIngresoNombresEspias(JLabel mensajeIngresoNombresEspias) {
		this.mensajeIngresoNombresEspias = mensajeIngresoNombresEspias;
	}

	public int getCantidadEspias() {
		return cantidadEspias;
	}

	public void setCantidadEspias(int cantidadEspias) {
		
		this.cantidadEspias = cantidadEspias;
		
		panelIngresoNombresEspias.removeAll();  
	    
		mensajeIngresoNombresEspias.setText("Definir los nombres de los espias");
		panelIngresoNombresEspias.add(mensajeIngresoNombresEspias);
	    
	    for (int i = 0; i < cantidadEspias; i++) {
	    	JPanel espiaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    	espiaPanel.setOpaque(false);
            
            JLabel espiaLabel = new JLabel("Espía " + i);
            espiaLabel.setOpaque(false);
            espiaPanel.add(espiaLabel);
            
            JTextField nombreEspia = new JTextField(10);
            espiaPanel.add(nombreEspia);
            
            nombresEspias.put(i, nombreEspia);

            panelIngresoNombresEspias.add(espiaPanel);
	    }
	    
	    panelIngresoNombresEspias.add(botonIngresoNombresEspias);  
	    panelIngresoNombresEspias.revalidate(); 
	    panelIngresoNombresEspias.repaint();  
	}

	public Map<Integer, JTextField> getNombresEspias() {
		return nombresEspias;
	}

	public void setNombresEspias(Map<Integer, JTextField> nombresEspias) {
		this.nombresEspias = nombresEspias;
	}
	
	public Image getFondo() {
		return fondo;
	}

	public void setFondo(Image fondo) {
		this.fondo = fondo;
	}
}
