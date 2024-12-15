package interfazVisual;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelDibujoGrafo extends JPanel{

	private static final long serialVersionUID = 1L;
	private GrafoVista grafo;
	private Image fondo;
	private JButton botonReiniciar;

	public PanelDibujoGrafo(GrafoVista grafo, int anchoPanel, int altoPanel) {
		this.grafo=grafo;
        setLayout(null); 
        setBorder(null);
        this.fondo = Toolkit.getDefaultToolkit().getImage("interfazVisual/images/mapa.jpg");

        JLabel titulo = new JLabel("Red de espias bajo algoritmo de Prim");
        titulo.setForeground(new Color(139, 69, 19));
        titulo.setFont(new Font("Garamond", Font.BOLD, 24));
        titulo.setBounds((anchoPanel/2)-150, 10, 500, 30);
        add(titulo);

        botonReiniciar = new JButton("Comenzar de nuevo");
        botonReiniciar.setBounds((anchoPanel/2)-100 ,520 , 200, 30);
        botonReiniciar.setFont(new Font("Garamond", Font.BOLD, 14));
        botonReiniciar.setForeground(new Color(139, 69, 19));
        botonReiniciar.setBackground(new Color(247, 241, 229));
        add(botonReiniciar);
	}

    public void setGrafo(GrafoVista nuevoGrafo) {
        this.grafo = nuevoGrafo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        if (grafo != null) {
            dibujarGrafo(g);
        }
    }

    private void dibujarGrafo(Graphics g) {
        g.setColor(Color.BLACK);

        for (AristaVista arista : grafo.getAristas()) {
            g.drawLine(arista.getOrigen().getX(), arista.getOrigen().getY(), arista.getDestino().getX(), arista.getDestino().getY());
            int midX = (arista.getOrigen().getX() + arista.getDestino().getX()) / 2;
            int midY = (arista.getOrigen().getY() + arista.getDestino().getY()) / 2;

            if (arista.getOrigen().getY() == arista.getDestino().getY()) {
                midY = midY - 15;
            } else {
                midX = midX - 15;
            }
            dibujarTexto(g, String.valueOf(arista.getPesoArista()), midX, midY);
        }

        for (VerticeVista vertice : grafo.getVertices()) {
            g.setColor(Color.RED);
            g.fillOval(vertice.getX() - 15, vertice.getY() - 15, 30, 30);
            g.setColor(Color.WHITE);

            String textoMostrar = (vertice.getNombreEspia() != null) ? vertice.getNombreEspia() : String.valueOf(vertice.getId());

            dibujarTexto(g, textoMostrar, vertice.getX(), vertice.getY());
        }
    }

    private void dibujarTexto(Graphics g, String text, int x, int y) {
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        g.drawString(text, x - textWidth / 2, y + textHeight / 4);
    }

	public JButton getBotonReiniciar() {
		return botonReiniciar;
	}

	public void setBotonReiniciar(JButton botonReiniciar) {
		this.botonReiniciar = botonReiniciar;
	}
}