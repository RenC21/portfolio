package interfazVisual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import presentador.Presentador;

public class VentanaInicio {

	private JFrame frame;
	private Presentador presentador;
	private JPanel panelVisualizado, panelVisualizacionGrafo;
	private CardLayout cardLayout;
	private int cantidadEspiasSeleccionada;
	private ArrayList<Tupla<Integer,Integer>> aristasSeleccionadas;
	private GrafoVista grafoVista;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
		
		cardLayout = new CardLayout();
		presentador = new Presentador();
        panelVisualizado = new JPanel(cardLayout); 
        this.aristasSeleccionadas=new ArrayList<Tupla<Integer,Integer>>();
        
		IngresoEspias ingresoEspias = new IngresoEspias();
		IngresoNombresEspias ingresoNombresEspias = new IngresoNombresEspias(0);
		ConfiguracionEspias configuracionEspias = new ConfiguracionEspias(0);
		ConfiguracionProbabilidadAristas configuracionProbabilidadAristas = new ConfiguracionProbabilidadAristas();

		JPanel panelIngresoEspias = ingresoEspias.getPanelIngresoEspias();
		JPanel panelIngresoNombresEspias = ingresoNombresEspias.getPanelIngresoNombresEspias();
		JPanel panelConfiguracionEspias = configuracionEspias.getPanelConfiguracionEspias();
		JPanel panelConfiguracionProbabilidadAristas = configuracionProbabilidadAristas.getPanelConfiguracionProbabilidadAristas();

		JButton botonCantidadEspias = ingresoEspias.getBotonIngresoEspias();
		JButton botonIngresoNombresEspias = ingresoNombresEspias.getBotonIngresoNombresEspias();
		JButton botonConfiguracionEspias = configuracionEspias.getBotonContinuar();
		JButton botonConfiguracionProbabilidadAristas = configuracionProbabilidadAristas.getBotonConfiguracionProbabilidadAristas();
		
		panelVisualizacionGrafo = new JPanel(new BorderLayout());

		panelVisualizado.add(panelIngresoEspias, "Panel 1");
		panelVisualizado.add(panelIngresoNombresEspias, "Panel 2");
        panelVisualizado.add(panelConfiguracionEspias, "Panel 3");
        panelVisualizado.add(panelConfiguracionProbabilidadAristas, "Panel 4");
        panelVisualizado.add(panelVisualizacionGrafo, "Panel 5");
        
        frame.getContentPane().add(panelVisualizado, BorderLayout.CENTER);
        frame.add(panelVisualizado, BorderLayout.CENTER);
        frame.setVisible(true);
        
	    
        botonCantidadEspias.addActionListener(e -> {
	    	cantidadEspiasSeleccionada = (int) ingresoEspias.getCampoCantidadEspias().getValue();
	    	ingresoNombresEspias.setCantidadEspias(cantidadEspiasSeleccionada);
            grafoVista = new GrafoVista(cantidadEspiasSeleccionada);
            presentador.solicitarInicializacionDeGrafo(cantidadEspiasSeleccionada);
            ArrayList<VerticeVista> vertices = new ArrayList<VerticeVista>();
            
            for (int i=0; i < cantidadEspiasSeleccionada; i++) {
            	VerticeVista vertice = new VerticeVista(i,0,0);
            	vertices.add(vertice);
            }
            
            grafoVista.setVertices(vertices);
            
            cardLayout.show(panelVisualizado, "Panel 2");
        });
        

        botonIngresoNombresEspias.addActionListener(e -> {
            Map<Integer, JTextField> espiasConNombre = ingresoNombresEspias.getNombresEspias();
            ArrayList<String> nombresEspias = new ArrayList<>();
            
            espiasConNombre.forEach((key, textField) -> {
                String nombre = textField.getText().isEmpty() ? key.toString() : textField.getText();
                nombresEspias.add(nombre);
                grafoVista.getVertices().get(key).setNombreEspia(nombre);
            });
            
            configuracionEspias.setNombresEspias(nombresEspias);
            configuracionProbabilidadAristas.setNombresEspias(nombresEspias);
            cantidadEspiasSeleccionada = ingresoNombresEspias.getCantidadEspias();
            configuracionEspias.setCantidadEspias(cantidadEspiasSeleccionada);
            
            cardLayout.show(panelVisualizado, "Panel 3");
        });

        
        botonConfiguracionEspias.addActionListener(e -> {
            Map<Integer, List<JCheckBox>> conjuntoRelacionesEspias = configuracionEspias.getEspiaCheckboxMap();
            boolean todosTienenRelaciones = true;  
            
            for (Map.Entry<Integer, List<JCheckBox>> conexionesEspia : conjuntoRelacionesEspias.entrySet()) {
                int espia = conexionesEspia.getKey();
                List<JCheckBox> conexiones = conexionesEspia.getValue();
                boolean tieneRelaciones = false; 
                
                for (JCheckBox checkbox : conexiones) {
                    if (checkbox.isSelected()) {
                        String vecinoTexto = checkbox.getText();
                        String nombreVecino = vecinoTexto.split(" ")[1];
                        VerticeVista verticeVecino = grafoVista.obtenerVerticePorNombre(nombreVecino);
                        
                        if (verticeVecino != null) {
                            int vecino = verticeVecino.getId();
                            
                            Tupla<Integer, Integer> parDeVecinos = new Tupla<>(espia, vecino);
                            boolean flagTuplasIguales = false;
                            
                            for (Tupla<?, ?> tuplaExistente : this.aristasSeleccionadas) {
                                if (tuplaExistente.compararTuplas(parDeVecinos)) {
                                    flagTuplasIguales = true;
                                    break;
                                }
                            }
                            
                            if (!flagTuplasIguales) {
                                this.aristasSeleccionadas.add(parDeVecinos);
                            }
                            
                            tieneRelaciones = true; 
                        }
                    }
                }
                
                if (!tieneRelaciones) {
                    todosTienenRelaciones = false; 
                    break;
                }
            }
            
            if (todosTienenRelaciones) {
                configuracionProbabilidadAristas.setListaVecinos(this.aristasSeleccionadas);
                cardLayout.show(panelVisualizado, "Panel 4");
            } else {
                JOptionPane.showMessageDialog(null, "Cada espía debe tener al menos una relación establecida con otro espia. Complete todas las relaciones entre espias", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        
        botonConfiguracionProbabilidadAristas.addActionListener(e -> {
            configuracionProbabilidadAristas.obtenerValoresSpinners().forEach((tupla, pesoArista) -> 
                grafoVista.agregarArista(new VerticeVista(tupla.getPrimerElemento(), 0, 0), 
                                         new VerticeVista(tupla.getSegundoElemento(), 0, 0), 
                                         pesoArista)
            );
            
            presentador.conversionGrafoVistaAGrafo(grafoVista);
            
            if (presentador.grafoEsContiguo()) {
                presentador.solicitarGeneracionArbolMinimo();
                presentador.conversionGrafoAGrafoVista();
                GrafoVista grafoAGM = presentador.getGrafoVista();

                for (int i = 0; i < grafoAGM.getVertices().size(); i++) {
                    grafoAGM.getVertices().get(i).setNombreEspia(grafoVista.getVertices().get(i).getNombreEspia());
                }
                
                mostrarGrafo(grafoAGM);
                
                cardLayout.show(panelVisualizado, "Panel 5");
            }
            else {
            	JOptionPane.showMessageDialog(null, "Las relaciones establecidas no son contiguas. Por favor, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
            	cardLayout.show(panelVisualizado, "Panel 1");
            	panelVisualizacionGrafo.revalidate();
        	    panelVisualizacionGrafo.repaint();
            }
            
        });
        
        frame.setVisible(true);
	}

	
	public void mostrarGrafo(GrafoVista grafoVista) {
	    PanelDibujoGrafo panelDibujo = new PanelDibujoGrafo(grafoVista, frame.getWidth(), frame.getHeight());
	    
	    panelVisualizacionGrafo.removeAll();
	    panelVisualizacionGrafo.add(panelDibujo, BorderLayout.CENTER);
	    
	    panelDibujo.getBotonReiniciar().addActionListener(e -> cardLayout.show(panelVisualizado, "Panel 1"));
	    
	    panelVisualizacionGrafo.revalidate();
	    panelVisualizacionGrafo.repaint();
	}
  
	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Temible operario del recontraespionaje");
		frame.setBounds(10, 10, 850, 700);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
