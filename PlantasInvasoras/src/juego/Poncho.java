package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Poncho {
	private Image img;
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private double escala;
	private int vidas;
	private String direccion;
	private int angulo;
	
	public Poncho(String url , int x, int y, int alto, int ancho, double escala) {
		this.img=Herramientas.cargarImagen(url);
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.escala=escala;
		this.vidas=3;
		this.direccion="arriba";
		}
	
	public void dibujar(Entorno entorno){
		entorno.dibujarImagen(this.img, this.x, this.y,angulo, this.escala);
	}
	
	public void moverIzquierda(){
		this.x = this.x - 2;
	}
	
	public void moverDerecha(){
		this.x = this.x + 2;
	}
	
	public void moverArriba(){
		this.y = this.y - 2;
	}
	
	public void moverAbajo(){
		this.y = this.y + 2;
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
	
	public String getDireccion() {
		return this.direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion=direccion;
	}
	public void setAngulo(String direccion) {
		if (this.direccion=="arriba") {
			this.angulo=0;
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
	
	public void setImg(Image img) {
		this.img=img;
	}
	public int getVidas() {
		return this.vidas;
	}
	public void setVidas() {
		this.vidas=3;
	}
	public void perderVida() {
		this.vidas--;
	}
	
	
	
	public Rayo disparar(String direccion) {
		Rayo rayo = new Rayo("Imagenes/rayo.png",this.x, this.y, 23, 15, 0.07, 5, direccion);
		rayo.setAngulo(direccion);
		return rayo;
	}
	

}
