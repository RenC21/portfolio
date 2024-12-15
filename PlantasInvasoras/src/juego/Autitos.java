package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Autitos {
	private Image img;
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int velocidad;
	private double escala;
	
	public Autitos (String url , int x, int y, int alto, int ancho, double escala, int velocidad) {
		this.img=Herramientas.cargarImagen(url);
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.velocidad=velocidad;
		this.escala=escala;
		}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(this.img, this.x, this.y,0, this.escala);
	}
	
	public void mover(String direccion) {
		if(direccion=="derecha") {
			this.x=this.x+this.velocidad;
		}
		if(direccion=="izquierda"){
			this.x=this.x-this.velocidad;
		}
		if(direccion=="arriba") {
			this.y=this.y-this.velocidad;
		}
		if(direccion=="abajo") {
			this.y=this.y+this.velocidad;
		}	
	}	
	
	public int getX()
	{
		return this.x;
	}
	

	public int getY() {
		return this.y;
	}
	
	public int getAncho(){
		return this.ancho;
	}

	public int getAlto() {
		return this.alto;
	}
	
	public int setX(int x2) {
		return this.x = x2;
	}
	public int setY(int y2) {
		return this.y = y2;
	}


}
