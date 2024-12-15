package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Pantallas {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private String screen;
	private Image img;
	private Image continuar;
	private Image fondoInicio;
	private Image fin;
	private Image win;
	private Image enter;
	private Image ctrl;
	private Image medalla;
	private Image ponchom;
	private Image plantam;
	private Image refreshw;
	private Image refreshd;
	private Image quit;
	private boolean avanzar;

	public Pantallas (int x, int y, int alto, int ancho, String screen) {
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.screen=screen;
		this.avanzar = false;
		this.continuar= Herramientas.cargarImagen("Imagenes/start.png");
		this.fondoInicio=Herramientas.cargarImagen("Imagenes/FondoMenu.png");
		this.fin=Herramientas.cargarImagen("Imagenes/gameover.png");
		this.win=Herramientas.cargarImagen("Imagenes/you-win.png");
		this.enter=Herramientas.cargarImagen("Imagenes/enter.png");
		this.ctrl=Herramientas.cargarImagen("Imagenes/control.png");
		this.medalla=Herramientas.cargarImagen("Imagenes/medalla.png");
		this.ponchom=Herramientas.cargarImagen("Imagenes/ponchomuerto.png");
		this.plantam=Herramientas.cargarImagen("Imagenes/plantamuerta.png");
		this.refreshw=Herramientas.cargarImagen("Imagenes/refreshW.png");
		this.refreshd=Herramientas.cargarImagen("Imagenes/refreshD.png");
		this.quit=Herramientas.cargarImagen("Imagenes/logout.png");
}	
	public void dibujarMenu (Entorno entorno) {
		if (this.screen.equals("Menu")) {
			entorno.dibujarImagen(this.fondoInicio, entorno.ancho()/2,entorno.alto()/2,0,0.5);
			entorno.dibujarImagen(this.continuar, entorno.ancho()/2, entorno.alto()/2+100, 0,0.6);
			entorno.dibujarImagen(this.enter, entorno.ancho()/2,entorno.alto()-80, 0,0.27);
			entorno.dibujarImagen(this.ctrl, entorno.ancho()-60,entorno.alto()-60, 0,0.12);
			entorno.dibujarImagen(this.quit,entorno.ancho()-140,entorno.alto()-60,0,0.12);
		}
	}
	
	public void dibujarFinal(Entorno entorno, int puntaje, int eliminados) {
		if (this.screen.equals("Derrota")) {
			Color verde=new Color(57,120,56);
			entorno.dibujarRectangulo(entorno.ancho()/2, entorno.alto()/2, entorno.ancho(), entorno.alto(), 0, verde);
			entorno.dibujarImagen(this.fin, entorno.ancho()/2, 180, 0, 0.8);
			entorno.dibujarImagen(this.enter, entorno.ancho()/2+100,entorno.alto()-80, 0,0.27);
			entorno.dibujarImagen(this.ctrl, entorno.ancho()/2-100,entorno.alto()-80, 0,0.27);
			entorno.dibujarImagen(this.ponchom, entorno.ancho()/2,entorno.alto()/2+100, 0,0.27);
			entorno.cambiarFont("Arial Rounded MT Bold", 35,Color.black);
			entorno.escribirTexto("Puntaje: " + String.valueOf(puntaje), entorno.ancho()/2+120 , entorno.alto()/2+110);
			entorno.escribirTexto("Eliminados: " + String.valueOf(eliminados), 50 , entorno.alto()/2+110);
			entorno.dibujarImagen(this.quit,120,entorno.alto()-80,0,0.22);
			entorno.dibujarImagen(this.refreshd,entorno.ancho()-120,entorno.alto()-80,0,0.22);
		}
		if (this.screen.equals("Victoria")) {
			Color naranja=new Color(232,141,74);
			entorno.dibujarRectangulo(entorno.ancho()/2, entorno.alto()/2, entorno.ancho(), entorno.alto(), 0, naranja);
			entorno.dibujarImagen(this.win, entorno.ancho()/2, 190, 0, 0.7);
			entorno.dibujarImagen(this.enter, entorno.ancho()/2+120,entorno.alto()-80, 0,0.27);
			entorno.dibujarImagen(this.ctrl, entorno.ancho()/2-120,entorno.alto()-80, 0,0.27);
			entorno.dibujarImagen(this.medalla, entorno.ancho()/2+130,95, 0,0.28);
			entorno.dibujarImagen(this.plantam,entorno.ancho()/2,entorno.alto()/2+130,0,0.3);
			entorno.cambiarFont("Arial Rounded MT Bold", 35,Color.black);
			entorno.escribirTexto("Puntaje: " + String.valueOf(puntaje), entorno.ancho()/2+120 , entorno.alto()/2+110);
			entorno.escribirTexto("Eliminados: " + String.valueOf(eliminados), 50 , entorno.alto()/2+110);
			entorno.dibujarImagen(this.quit,120,entorno.alto()-80,0,0.22);
			entorno.dibujarImagen(this.refreshw,entorno.ancho()-120,entorno.alto()-80,0,0.22);
		}
	}
	
	public void setAvanzar(boolean t){
		this.avanzar=t;
	}
	
	public boolean getAvanzar() {
		return this.avanzar;
	}
}