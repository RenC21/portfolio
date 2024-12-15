package juego;

import java.awt.*;

import entorno.Entorno;
import entorno.Herramientas;

public class Manzanas {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private Point punto1;
	private Point punto2;
	private Point punto3;
	private Point punto4;
	private Image img;
	private double escala;
	
	public Manzanas(int x, int y, int ancho, int alto,String imagen,double escala)
	{
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.punto1=new Point((this.x-(this.ancho/2)),(this.y-(this.alto/2)));
		this.punto2=new Point((this.x+(this.ancho/2)),(this.y-(this.alto/2)));
		this.punto3=new Point((this.x-(this.ancho/2)),(this.y+(this.alto/2)));
		this.punto4=new Point((this.x+(this.ancho/2)),(this.y+(this.alto/2)));
		this.img= Herramientas.cargarImagen(imagen);
		this.escala=escala;
	}
	
	public void dibujar(Entorno entorno){
		entorno.dibujarImagen(img, this.x, this.y, 0, escala);
	}
		
	public int getX()
	{
		return this.x;
	}
	
	public int getAncho()
	{
		return this.ancho;
	}

	public int getY() {
		return y;
	}

	public int getAlto() {
		return alto;
	}
	public Point getPoint(int n) {
		if(n==1) {
			return this.punto1;
		}else if(n==2) {
			return this.punto2;
		}else if(n==3) {
			return this.punto3;
		}else {
			return this.punto4;
		}
	}
}
