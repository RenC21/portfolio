package interfazVisual;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

public class Pieza extends JButton{
	
	 private boolean vacia;
	 private int xActual;
	 private int yActual;

	//Constructor de Pieza
	public Pieza(boolean vacia, String texto, int tamanioPieza, int xInicial, int yInicial) {
		 this.setText(texto);
		 this.setPreferredSize(new Dimension(tamanioPieza,tamanioPieza));
		 this.xActual = xInicial;
		 this.yActual = yInicial;
		 
		 this.vacia = vacia;
		 
		 if (!vacia) {
			 this.setBackground(new Color(255,244,228));
		 }
		 else {
			 this.setBackground(new Color(60,45,85));
		 }
		 this.setOpaque(true);
	}

	
	//GETTERS/SETTERS //
	
	public int getxActual() {
		return xActual;
	}

	public void setxActual(int xActual) {
		this.xActual = xActual;
	}

	public int getyActual() {
		return yActual;
	}

	public void setyActual(int yActual) {
		this.yActual = yActual;
	}

	public boolean isVacia() {
		return vacia;
	}

	public void setVacia(boolean vacia) {
		this.vacia = vacia;
	}
}
