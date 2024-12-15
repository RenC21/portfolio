package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Rayo {
	private int x;
	private int y;
	private double escala;
	private int ancho;
	private int alto;
	
	private int velocidad;
	
	private String direccion;
	private Image img;
	private int angulo;
	
	private boolean disparado = false;
	
	public Rayo(String url , int x, int y, int alto, int ancho, double escala,int velocidad, String direccion) {
		this.x = x;
		this.y = y;
		this.escala=escala;
		this.velocidad=velocidad;
		this.direccion=direccion;
		this.ancho = ancho;
		this.alto = alto;
		this.img=Herramientas.cargarImagen(url);
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(this.img, this.x, this.y, this.angulo, escala);
	}
	
	public void mover() {
		if (direccion=="arriba") 
		this.y = this.y - this.velocidad;
		if (direccion=="abajo")
		this.y = this.y + this.velocidad;
		if (direccion=="izquierda")
		this.x = this.x - this.velocidad;
		if (direccion=="derecha")
		this.x = this.x + this.velocidad;
	}
	
	//GETTERS
	public boolean getDisparado() {
		return this.disparado;
	}
	
	public int getX(){
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
	
	//SETTERS
	void sety(int y) {
		this.y -= y;
	}
	
	void setx(int x) {
		this.x -= x;
	}
	
	public void setDisparado(boolean disparado) {
		this.disparado = disparado;
	}
	
	public void setAngulo(String direccion) {
		if (this.direccion=="arriba") {
			this.angulo=600;
		}
		if (this.direccion=="derecha") {
			this.angulo=300;
		}
		if (this.direccion=="izquierda") {
			this.angulo=-300;
		}
		if (this.direccion=="abajo") {
			this.angulo=0;
		}
	}
	
}	



	

