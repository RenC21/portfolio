package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Plantas {
	private Image img;
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private double escala;
	private String direccion;
	private int velocidad;

	
	public Plantas (String url , int x, int y, int alto, int ancho, double escala, String direccion, int velocidad) {
		this.img=Herramientas.cargarImagen(url);
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.escala=escala;
		this.direccion=direccion;
		this.velocidad=velocidad;
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(this.img, this.x, this.y,0, escala);
	}
	
	public void mover() {
		if(this.direccion.equals("derecha")) {
			this.x=this.x+this.velocidad;
		}
		if(this.direccion.equals("izquierda")) {
			this.x=this.x-this.velocidad;
		}
		if(this.direccion.equals("arriba")) {
			this.y=this.y-this.velocidad;
		}
		if(this.direccion.equals("abajo")) {
			this.y=this.y+this.velocidad;
		}
		
		}	
	
	public int getX(){
		return this.x;
	}
	
	public int getAncho(){
		return this.ancho;
	}

	public int getY() {
		return this.y;
	}

	public int getAlto() {
		return this.alto;
	}

	
	public void setDireccion(String dir) {
		this.direccion=dir;
		if(dir.equals("izquierda")) {
			this.y=this.y+50;
		}
		if(dir.equals("derecha")) {
			this.y=this.y-50;
		}
		if(dir.equals("arriba")) {
			this.x=this.x+50;
		}
		if(dir.equals("abajo")) {
			this.x=this.x-50;
		}
	}
	
	public String getDireccion() {
		return this.direccion;
	}
	
	public Bola lanzarBola(String direccion) {
		Bola bola = new Bola("imagenes/bola-de-fuego.png", this.x,this.y,20,20,0.08,3,this.direccion);
		bola.setAngulo(direccion);
		return bola;
	}
	
	
}
