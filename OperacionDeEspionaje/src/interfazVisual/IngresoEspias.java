package interfazVisual;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class IngresoEspias {

	private JPanel panelIngresoEspias;
	private JButton botonIngresoEspias;
	private JLabel nombreJuego;
	private JLabel mensajeIngresoCantidadEspias;
	private JSpinner campoCantidadEspias;
	private Image fondo;

	public IngresoEspias() {
		fondo = Toolkit.getDefaultToolkit().getImage("interfazVisual/images/mapa.jpg");
		panelIngresoEspias = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        panelIngresoEspias.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.gridx = 0; 
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
	    nombreJuego = new JLabel("Temible operario del recontraespionaje");
	    nombreJuego.setFont(new Font("Garamond", Font.BOLD, 24));
	    gbc.gridy = 0; 
	    panelIngresoEspias.add(nombreJuego, gbc);
	    
	    mensajeIngresoCantidadEspias = new JLabel("Ingrese la cantidad de espias: ");
	    mensajeIngresoCantidadEspias.setFont(new Font("Garamond", Font.BOLD, 18));
	    gbc.gridy = 1; 
	    panelIngresoEspias.add(mensajeIngresoCantidadEspias, gbc);

	    SpinnerNumberModel modelo = new SpinnerNumberModel(1, 1, 10, 1);
	    campoCantidadEspias = new JSpinner(modelo);
	    Dimension spinnerSize = new Dimension(50, 20); 
	    campoCantidadEspias.setPreferredSize(spinnerSize);
	    gbc.gridy = 2; 
	    panelIngresoEspias.add(campoCantidadEspias, gbc);
	    
	    botonIngresoEspias = new JButton("Siguiente");
	    gbc.gridy = 3; 
	    panelIngresoEspias.add(botonIngresoEspias, gbc);
	    
	    panelIngresoEspias.repaint();
	}
	
 
	public JPanel getPanelIngresoEspias() {
		return panelIngresoEspias;
	}

	public void setPanelIngresoEspias(JPanel panelIngresoEspias) {
		this.panelIngresoEspias = panelIngresoEspias;
	}

	public JButton getBotonIngresoEspias() {
		return botonIngresoEspias;
	}

	public void setBotonIngresoEspias(JButton botonIngresoEspias) {
		this.botonIngresoEspias = botonIngresoEspias;
	}

	public JLabel getNombreJuego() {
		return nombreJuego;
	}

	public JSpinner getCampoCantidadEspias() {
		return campoCantidadEspias;
	}

	public void setNombreJuego(JLabel nombreJuego) {
		this.nombreJuego = nombreJuego;
	}

	public JLabel getMensajeIngresoCantidadEspias() {
		return mensajeIngresoCantidadEspias;
	}

	public void setMensajeIngresoCantidadEspias(JLabel mensajeIngresoCantidadEspias) {
		this.mensajeIngresoCantidadEspias = mensajeIngresoCantidadEspias;
	}
}
