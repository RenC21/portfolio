package interfazVisual;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ConfiguracionEspias {

	private JPanel panelConfiguracionEspias;
    private JLabel labelConfiguracionEspias;
    private int cantidadEspias;
    private JButton botonConfiguracionEspias;
    private Map<Integer, List<JCheckBox>> espiaCheckboxMap;
    private ArrayList<String> nombresEspias;
	private Image fondo;

    
    public ConfiguracionEspias(int cantidadEspias) {
    	
        this.cantidadEspias = cantidadEspias;
        this.nombresEspias = new ArrayList<>();
        
        fondo = Toolkit.getDefaultToolkit().getImage("interfazVisual/images/mapa.jpg");
        
        panelConfiguracionEspias = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (fondo != null) {
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        
        panelConfiguracionEspias.setLayout(new BoxLayout(panelConfiguracionEspias, BoxLayout.Y_AXIS));
        labelConfiguracionEspias = new JLabel("Defini las relaciones entre los espias");
        labelConfiguracionEspias.setFont(new Font("Garamond", Font.BOLD, 24));
        panelConfiguracionEspias.repaint();
        panelConfiguracionEspias.add(labelConfiguracionEspias);
        
        for (int i = 0; i < cantidadEspias; i++) {
            JPanel espiaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel espiaLabel = new JLabel("Espía " + i);
            espiaPanel.add(espiaLabel);

            for (int j = 0; j < cantidadEspias; j++) {
                if (j != i) {
                    JLabel vecinoLabel = new JLabel("Vecino " + j);
                    espiaPanel.add(vecinoLabel);
                    
                }
            }
            panelConfiguracionEspias.add(espiaPanel);
        }

        botonConfiguracionEspias = new JButton("Continuar");
        botonConfiguracionEspias.setFont(new Font("Garamond", Font.BOLD, 18));
        panelConfiguracionEspias.add(botonConfiguracionEspias);
        espiaCheckboxMap = new HashMap<>();
        
    }


	public void setCantidadEspias(int cantidadEspias) {
		
	    this.cantidadEspias = cantidadEspias;
	    
	    panelConfiguracionEspias.removeAll(); 
	    
	    labelConfiguracionEspias.setText("Defini las relaciones entre los espias");
	    panelConfiguracionEspias.add(labelConfiguracionEspias);
	    
	   
	    for (int i = 0; i < cantidadEspias; i++) {
	        JPanel espiaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        
	        espiaPanel.setOpaque(false);
	        
	        JLabel espiaLabel = new JLabel("Espía " + nombresEspias.get(i));
	        espiaLabel.setOpaque(false);
	        espiaLabel.setFont(new Font("Garamond", Font.BOLD, 16));
	        espiaPanel.add(espiaLabel);
	        
	        
	        List<JCheckBox> checkboxes = new ArrayList<>();
	        for (int j = 0; j < cantidadEspias; j++) {
	            if (j != i) {
	            	JCheckBox  vecinoCheckbox = new JCheckBox ("Vecino " + nombresEspias.get(j));
	            	vecinoCheckbox.setOpaque(false);
	            	vecinoCheckbox.setFont(new Font("Garamond", Font.BOLD, 14));
	                checkboxes.add(vecinoCheckbox);
	                espiaPanel.add(vecinoCheckbox);  
	            }
	        }
            espiaCheckboxMap.put(i, checkboxes);
	        panelConfiguracionEspias.add(espiaPanel);  
	    }
	    
	    panelConfiguracionEspias.add(botonConfiguracionEspias);  
	    panelConfiguracionEspias.revalidate();  
	    panelConfiguracionEspias.repaint(); 
	}

	public JPanel getPanelConfiguracionEspias() {
		return panelConfiguracionEspias;
	}

	public void setPanelConfiguracionEspias(JPanel panelConfiguracionEspias) {
		this.panelConfiguracionEspias = panelConfiguracionEspias;
	}

	public JLabel getLabelConfiguracionEspias() {
		return labelConfiguracionEspias;
	}

	public void setLabelConfiguracionEspias(JLabel labelConfiguracionEspias) {
		this.labelConfiguracionEspias = labelConfiguracionEspias;
	}

	public JButton getBotonContinuar() {
		return botonConfiguracionEspias;
	}

	public void setBotonContinuar(JButton botonConfiguracionEspias) {
		this.botonConfiguracionEspias = botonConfiguracionEspias;
	}

	public int getCantidadEspias() {
		return cantidadEspias;
	}

	public Map<Integer, List<JCheckBox>> getEspiaCheckboxMap() {
		return espiaCheckboxMap;
	}

	public void setEspiaCheckboxMap(Map<Integer, List<JCheckBox>> espiaCheckboxMap) {
		this.espiaCheckboxMap = espiaCheckboxMap;
	}

	public ArrayList<String> getNombresEspias() {
		return nombresEspias;
	}


	public void setNombresEspias(ArrayList<String> nombresEspias) {
		this.nombresEspias = nombresEspias;
	}
}
