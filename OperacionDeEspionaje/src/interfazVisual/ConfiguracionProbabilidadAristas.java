package interfazVisual;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ConfiguracionProbabilidadAristas{
    private JPanel panelConfiguracionProbabilidadAristas;
    private JLabel labelConfiguracionProbabilidadAristas;
    private JButton botonConfiguracionProbabilidadAristas;
    private ArrayList<Tupla<Integer, Integer>> listaVecinos;
    private Map<Tupla<Integer, Integer>, JSpinner> spinnerMapa;
    private ArrayList<String> nombresEspias;
	private Image fondo;
   
    
    public ConfiguracionProbabilidadAristas() {
    	this.fondo = Toolkit.getDefaultToolkit().getImage("interfazVisual/images/mapa.jpg");
    	this.nombresEspias = new ArrayList<>();
        panelConfiguracionProbabilidadAristas = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (fondo != null) {
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        panelConfiguracionProbabilidadAristas.setLayout(new BoxLayout(panelConfiguracionProbabilidadAristas, BoxLayout.Y_AXIS));
        panelConfiguracionProbabilidadAristas.repaint();
        
        this.labelConfiguracionProbabilidadAristas = new JLabel("Defini la probabilidad de encuentro entre espias");
        labelConfiguracionProbabilidadAristas.setFont(new Font("Garamond", Font.BOLD, 24));
        panelConfiguracionProbabilidadAristas.add(labelConfiguracionProbabilidadAristas);
        
        this.botonConfiguracionProbabilidadAristas = new JButton("Generar mapa");
        botonConfiguracionProbabilidadAristas.setFont(new Font("Garamond", Font.BOLD, 18));
        panelConfiguracionProbabilidadAristas.add(botonConfiguracionProbabilidadAristas);
        this.spinnerMapa = new HashMap<>();
    }
    
    public void setListaVecinos(ArrayList<Tupla<Integer, Integer>> listaVecinos) {
    	
        this.listaVecinos = listaVecinos;
        
        panelConfiguracionProbabilidadAristas.removeAll(); 

        this.spinnerMapa.clear(); 

        panelConfiguracionProbabilidadAristas.add(labelConfiguracionProbabilidadAristas); 
        
        JPanel panelConfiguracionProbabilidad = new JPanel();
        panelConfiguracionProbabilidad.setOpaque(false);
        
        panelConfiguracionProbabilidad.setLayout(new GridLayout(0, 3, 10, 10)); 
        
        for (Tupla<Integer, Integer> tupla : this.listaVecinos) {
        	
            JPanel espiaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
            espiaPanel.setOpaque(false);
            JLabel arista = new JLabel(nombresEspias.get(tupla.getPrimerElemento()) + "-" + nombresEspias.get(tupla.getSegundoElemento()));
            arista.setFont(new Font("Garamond", Font.BOLD, 16));
            arista.setOpaque(false);
            
            SpinnerNumberModel modelo = new SpinnerNumberModel(0, 0, 1, 0.1);
            JSpinner spinnerProbabilidad = new JSpinner(modelo);
            spinnerProbabilidad.setOpaque(false);
            
            spinnerProbabilidad.setPreferredSize(new Dimension(40, 25));
            
            spinnerMapa.put(tupla, spinnerProbabilidad);
            
            espiaPanel.add(arista);
            espiaPanel.add(spinnerProbabilidad);
            
            panelConfiguracionProbabilidad.add(espiaPanel);
            panelConfiguracionProbabilidadAristas.add(panelConfiguracionProbabilidad);  
        }
        
        panelConfiguracionProbabilidadAristas.add(botonConfiguracionProbabilidadAristas); 
        panelConfiguracionProbabilidadAristas.revalidate();  
        panelConfiguracionProbabilidadAristas.repaint();  
    }

    

    public Map<Tupla<Integer, Integer>, Double> obtenerValoresSpinners() {
        Map<Tupla<Integer, Integer>, Double> valores = new HashMap<>();
        
        for (Map.Entry<Tupla<Integer, Integer>, JSpinner> entry : spinnerMapa.entrySet()) {
            Tupla<Integer, Integer> tupla = entry.getKey();
            JSpinner spinner = entry.getValue();
            double valor = Math.round((double) spinner.getValue() * 100.0) / 100.0;
            valores.put(tupla, valor); 
        }
        
        return valores;
    }

	
	public JPanel getPanelConfiguracionProbabilidadAristas() {
		return panelConfiguracionProbabilidadAristas;
	}

	public void setPanelConfiguracionProbabilidadAristas(JPanel panelConfiguracionProbabilidadAristas) {
		this.panelConfiguracionProbabilidadAristas = panelConfiguracionProbabilidadAristas;
	}

	public JLabel getLabelConfiguracionProbabilidadAristas() {
		return labelConfiguracionProbabilidadAristas;
	}

	public void setLabelConfiguracionProbabilidadAristas(JLabel labelConfiguracionProbabilidadAristas) {
		this.labelConfiguracionProbabilidadAristas = labelConfiguracionProbabilidadAristas;
	}

	public JButton getBotonConfiguracionProbabilidadAristas() {
		return botonConfiguracionProbabilidadAristas;
	}

	public void setBotonConfiguracionProbabilidadAristas(JButton botonConfiguracionProbabilidadAristas) {
		this.botonConfiguracionProbabilidadAristas = botonConfiguracionProbabilidadAristas;
	}

	public ArrayList<Tupla<Integer, Integer>> getListaVecinos() {
		return listaVecinos;
	}

	public ArrayList<String> getNombresEspias() {
		return nombresEspias;
	}

	public void setNombresEspias(ArrayList<String> nombresEspias) {
		this.nombresEspias = nombresEspias;
	}
}

	
	
    

    

  
