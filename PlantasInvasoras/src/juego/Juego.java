package juego;

import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;
import java.util.random.RandomGenerator;

import javax.swing.JFrame;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;	

	// Variables y métodos propios de cada grupo
	// ...
	
	private Pantallas menu;
	private Pantallas gameOver;
	private Pantallas win;
	
	private Poncho poncho;
	
	private Manzanas manzana1;
	private Manzanas manzana2;
	private Manzanas manzana3;
	private Manzanas manzana4;
	
	private Plantas [] listaPlantas;
	private Bola [] arregloBolas;
	private Point [] spawn1;
	private Point [] spawn2;
	private String[] direcciones;
	
	private int vidasPosicion;
	private Image[] vidas;
	private Image vidaNeg;
	private Image reloj;
	private Image score;
	private Image calles;
	
	private Point punto1;
	private Point punto2;
	private Point punto3;
	private Point punto4;
	private Point punto5;
	private Point punto6;
	private Point punto7;
	private Point punto8;
	private Point punto9;
	private Point punto10;
	private Point punto11;
	private Point punto12;
	private Point punto13;
	private Point punto14;
	private Point punto15;

	private Autitos autito1;
	private Autitos autito2;
	private Autitos autito3;
	private Autitos autito4;
	private Autitos autito5;
	
	private int time=4100;
	private int puntaje=0;
	private int eliminados=0;
	
	private int coolDown;
	private int timeBola=200;
	
	private int dx = 125;
	private int dy = 75;
	private Rayo shoot;
	
	
	Juego() {
		Random rand = new Random();
		// Inicializa el objeto entorno
//		Carga de pantallas
		this.entorno = new Entorno(this, " Plantas Invasoras - Grupo ... - v1", 800, 650);
		this.menu= new Pantallas (this.entorno.ancho()/2,this.entorno.alto()/2,this.entorno.ancho(),this.entorno.alto(),"Menu");
		this.gameOver= new Pantallas (this.entorno.ancho()/2,this.entorno.alto()/2,this.entorno.ancho(),this.entorno.alto(),"Derrota");
		this.win= new Pantallas (this.entorno.ancho()/2,this.entorno.alto()/2,this.entorno.ancho(),this.entorno.alto(),"Victoria");
		this.poncho=new Poncho("Imagenes/poncho.png",400,this.entorno.alto()-125,40,45,0.09);
		this.reloj =Herramientas.cargarImagen("Imagenes/reloj-de-arena.png");
		Image vida =Herramientas.cargarImagen("Imagenes/hueso.png");
		this.vidasPosicion=50;
		this.vidaNeg=Herramientas.cargarImagen("Imagenes/hueso-roto.png");
		this.score=Herramientas.cargarImagen("Imagenes/plantamuerta.png");
		this.calles=Herramientas.cargarImagen("Imagenes/calles.png");

//		Puntos asignados para Spawn de plantas
		this.punto1 = new Point (25,25); //derecha abajo 
		this.punto2 = new Point (this.entorno.ancho()/2 -25 ,25); //derecha abajo 
		this.punto3 = new Point (725,75); // izquierda abajo 
		this.punto4 = new Point (25,275);//derecha abajo 
		this.punto5 = new Point (725,325);// izquierda abajo 
		this.punto6 = new Point (75,525); //derecha arriba 
		this.punto7 = new Point (this.entorno.ancho()/2-25,275);//abajo derecha 
		this.punto8 = new Point (775,575);//derecha arriba 
		this.punto9 = new Point (this.entorno.ancho()/2-25,725);//derecha abajo
		this.punto10 = new Point (775,this.entorno.alto()/2-25); //derecha arriba
		this.punto11 = new Point (75,75); //izquierda arriba
		this.punto12 = new Point (725,575); //izquierda abajo
		this.punto13 = new Point (this.entorno.ancho()/2+25,775); //izquierda arriba
		this.punto14 = new Point (25,725); //abajo derecha
		this.punto15 = new Point (75,this.entorno.alto()/2+25); //arriba izquierda
		
		
//		Creacion manzanas
		this.manzana1 = new Manzanas(dx+100, dy+100, 250, 150, "Imagenes/miprimermanzanita.png",1);
		this.manzana2 = new Manzanas(dx+450, dy+100, 250, 150, "Imagenes/miprimermanzanita.png",1);
		this.manzana3 = new Manzanas(dx+100, dy+350, 250, 150, "Imagenes/miprimermanzanita.png",1);
		this.manzana4 = new Manzanas(dx+450, dy+350, 250, 150, "Imagenes/miprimermanzanita.png",1);
		
		this.coolDown=0;
		
		listaPlantas = new Plantas[4];
		spawn1=new Point[] {punto1,punto2,punto3,punto4,punto5,punto6,punto7,punto8};
		spawn2=new Point[] {punto1,punto2,punto3,punto4,punto5,punto6,punto7,punto8,punto9,punto10,punto11,punto12,punto13,punto14,punto15};
		direcciones = new String[] {"abajo","derecha","arriba","izquierda"};
		
//		Creacion vidas Interfaz
		vidas = new Image[this.poncho.getVidas()];
		for(int i=0; i<vidas.length;i++) {
			vidas[i]=vida;
		}
		
//		Creacion plantas
		for(int i= 0; i<=3; i++) {
			Plantas planta;
			String dir = null;
			int pos = rand.nextInt(0, 7);
			while (spawn1[pos]==null) {
				 pos = rand.nextInt(0, 7);
			}
			if (spawn1[pos]==punto1 || spawn1[pos]==punto2 || spawn1[pos]==punto4 || spawn1[pos]==punto7) {
				dir = direcciones[rand.nextInt(2)];
			}
			if (spawn1[pos]==punto6 || spawn1[pos]==punto8) {
				dir = direcciones[rand.nextInt(2)+1];
			}
			if (spawn1[pos]==punto3 || spawn1[pos]==punto5) {
				int r = rand.nextInt(2);
				if (r==1) {
					dir = direcciones[3];
				}else if (r==0) {
					dir = direcciones[0];
				}
			}
			planta = new Plantas("imagenes/planta.png", spawn1[pos].x,spawn1[pos].y,40,28,0.09,dir,2);
			listaPlantas[i]= planta;
			spawn1[pos]=null;
		}
		
		
		arregloBolas=new Bola[30];
		
//		creacion autos
		this.autito1 = new Autitos("Imagenes/car3.png", 25,this.entorno.alto()-75,20,10,0.11,3);
		this.autito2 = new Autitos("Imagenes/car2.png", 25,this.entorno.alto()/2-50,20,10,0.11,4);
		this.autito3 = new Autitos("Imagenes/car4.png", this.entorno.ancho()-25,25,20,10,0.11,3);
		this.autito4 = new Autitos("Imagenes/car.png", 25,-2000,20,10,0.11,4);
		this.autito5 = new Autitos("Imagenes/car5.png", this.entorno.ancho()-25,this.entorno.alto()+800,20,10,0.11,3);
		
		                       		

		// Inicializar lo que haga falta para el juego
		// ...
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		
		//Pantalla inicio
		if(!this.menu.getAvanzar()) {
			menu.dibujarMenu(this.entorno);
			if(this.entorno.sePresiono(this.entorno.TECLA_ENTER)) {
				this.menu.setAvanzar(true);
			}else if(this.entorno.sePresiono(this.entorno.TECLA_CTRL)) {
				this.entorno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.entorno.setLocationRelativeTo(null);
				this.entorno.setVisible(false);
			}
		//inicio de juego
		}else if (this.poncho != null && this.time>=0){
//			Dibujar fondo e interfaz
			this.entorno.dibujarImagen(calles, entorno.ancho()/2, entorno.alto()/2-25, 0, 1);
			this.entorno.dibujarRectangulo(entorno.ancho()/2,entorno.alto()-20,this.entorno.ancho(),50,0,Color.getHSBColor(2,20 ,20));
			
//			Dibujar Manzanas
			Manzanas[] listaManzanas= {manzana1, manzana2, manzana3, manzana4};
			this.manzana1.dibujar(this.entorno);
			this.manzana2.dibujar(this.entorno);
			this.manzana3.dibujar(this.entorno);
			this.manzana4.dibujar(this.entorno);
			
//			Dibujar Poncho
			if (this.poncho!=null)
				this.poncho.dibujar(this.entorno);
			
//			Dibujar Autos
			if (this.autito1 != null) {
				this.autito1.dibujar(this.entorno);
			}
			if (this.autito2 != null) {
				this.autito2.dibujar(this.entorno);
			}
			if (this.autito3 != null) {
				this.autito3.dibujar(this.entorno);
			}
			if (this.autito4 != null ) {
				this.autito4.dibujar(this.entorno);
			}
			if (this.autito5 != null ) {
				this.autito5.dibujar(this.entorno);
			}
			
//			Interfaz inferior
			this.entorno.dibujarRectangulo(entorno.ancho()/2,entorno.alto()-20,this.entorno.ancho(),50,0,Color.getHSBColor(2,20 ,20));
//			
//			Dibujar Vidas
			for(int i=0; i<vidas.length;i++) {
				this.entorno.dibujarImagen(vidas[i],this.vidasPosicion,this.entorno.alto()-20,0,0.1);
				vidasPosicion+=60;
			}
			vidasPosicion=50;
			
//			Dibujar Plantas
			for (int i=0 ; i<listaPlantas.length; i++) {
				if (listaPlantas[i]!=null) 
				this.listaPlantas[i].dibujar(this.entorno);
			}
	
//			Tiempo
			if (time>=0)
			time--;
			
//			Interfaz Tiempo
			this.entorno.dibujarImagen(reloj, this.entorno.ancho()/2-50, this.entorno.alto()-22, 0,0.07);
			this.entorno.cambiarFont("Arial Rounded MT Bold", 40, Color.black);
			this.entorno.escribirTexto(String.valueOf(this.time/100), this.entorno.ancho()/2-20, this.entorno.alto()-7);
			
//			Movimiento Poncho
			if(this.poncho!=null){
				if(this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) && 
						this.poncho.getX() + this.poncho.getAncho()/2 < this.entorno.ancho() && !this.entorno.estaPresionada(this.entorno.TECLA_ABAJO) && !this.entorno.estaPresionada(this.entorno.TECLA_ARRIBA) && !this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)) {
					this.poncho.moverDerecha();
					this.poncho.setDireccion("derecha");
					this.poncho.setAngulo(this.poncho.getDireccion());
				} 		
				
				if(this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA) &&
						this.poncho.getX() - this.poncho.getAncho()/2 > 0 && !this.entorno.estaPresionada(this.entorno.TECLA_ABAJO) && !this.entorno.estaPresionada(this.entorno.TECLA_ARRIBA) && !this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)) {
					this.poncho.moverIzquierda();
					this.poncho.setDireccion("izquierda");
					this.poncho.setAngulo(this.poncho.getDireccion());
				}
				
				if(this.entorno.estaPresionada(this.entorno.TECLA_ABAJO) && 
						this.poncho.getY() + this.poncho.getAlto()/2 < this.entorno.alto()-50 && !this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) && !this.entorno.estaPresionada(this.entorno.TECLA_ARRIBA) && !this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)) {
					this.poncho.moverAbajo();
					this.poncho.setDireccion("abajo");
					this.poncho.setAngulo(this.poncho.getDireccion());
				}
				
				if(this.entorno.estaPresionada(this.entorno.TECLA_ARRIBA) && 
						this.poncho.getY() - this.poncho.getAlto()/2 > 0 && !this.entorno.estaPresionada(this.entorno.TECLA_ABAJO) && !this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) && !this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)) {
					this.poncho.moverArriba();
					this.poncho.setDireccion("arriba");
					this.poncho.setAngulo(this.poncho.getDireccion());
			}
			}
			
//			Disparo y su interaccion
			if (entorno.sePresiono(entorno.TECLA_ESPACIO) && shoot == null) {
				shoot = poncho.disparar(this.poncho.getDireccion());
			}
			if (shoot != null) {
				shoot.dibujar(entorno);
				shoot.mover();
				if(colisionRayoPared(this.shoot)&& this.shoot!=null) {
					this.shoot=null;
				}
				for (int i =0;i<listaPlantas.length;i++) {
					if(this.listaPlantas[i]!=null) {
						if((colisionRayoPlanta(listaPlantas[i],this.shoot)&& this.shoot!=null)) {
							this.listaPlantas[i]=null;
							this.shoot=null;
							puntaje+=5;
							eliminados+=1;
						}
					}
				}
				if ((colisionRayoAutito(this.autito1,this.shoot)||colisionRayoAutito(this.autito2,this.shoot)||colisionRayoAutito(this.autito3,this.shoot)||colisionRayoAutito(this.autito4,this.shoot)||colisionRayoAutito(this.autito5,this.shoot))&& this.shoot!=null){
					this.shoot=null;
				}
				for (int i =0;i<listaManzanas.length;i++) {
					if((colisionRayoManzana(listaManzanas[i],this.shoot)&& this.shoot!=null)) {
						this.shoot=null;
					}
				}
				for (int i =0;i<arregloBolas.length;i++) {
					if (colisionRayoBolita(arregloBolas[i], this.shoot)&& this.arregloBolas[i]!= null && this.shoot !=null) {
						this.arregloBolas[i]=null;
						this.shoot = null;
					}
				}	
			}
			
			
//			Interfaz Puntaje
			this.entorno.dibujarImagen(score, this.entorno.ancho()-180, this.entorno.alto()-15, 0,0.09);
			this.entorno.cambiarFont("Arial Rounded MT Bold", 40, Color.black);
			this.entorno.escribirTexto(String.valueOf(this.puntaje)+"("+String.valueOf(this.eliminados)+")", this.entorno.ancho()-150, this.entorno.alto()-9);
			

//			Respwan plantas
			int anterior=0;
			for(int i= 0; i<listaPlantas.length; i++) {
				Random random=new Random();
				if (this.listaPlantas[i]==null) {
					Plantas planta;
					String dir = null;
					int pos = random.nextInt(0, 7);
					while (pos==anterior) {
						 pos = random.nextInt(0, 7);
					}
//					derecha abajo
					if (spawn2[pos]==punto1 || spawn2[pos]==punto2 || spawn2[pos]==punto4 || spawn2[pos]==punto7 || spawn2[pos]==punto9 || spawn2[pos]==punto14) {
						dir = direcciones[random.nextInt(2)];
					}
//					derecha arriba
					if (spawn2[pos]==punto6 || spawn2[pos]==punto8 || spawn2[pos]==punto10) {
						dir = direcciones[random.nextInt(2)+1];
					}
//					Izquierda arriba
					if (spawn2[pos]==punto13 || spawn2[pos]==punto15) {
						dir = direcciones[random.nextInt(2)+2];
					}
//					izquierda abajo
					if (spawn2[pos]==punto3 || spawn2[pos]==punto5 || spawn2[pos]==punto12) {
						int r = random.nextInt(2);
						if (r==1) {
							dir = direcciones[3];
						}else if (r==0) {
							dir = direcciones[0];
						}
					}
					planta = new Plantas("imagenes/planta.png", spawn2[pos].x,spawn2[pos].y,40,28,0.09,dir,2);
					listaPlantas[i]= planta;
					anterior=pos;
				}
			}
			
			

		
			
	//		Movimiento Plantas
			
			for(int i=0; i< listaPlantas.length; i++) {
				if(this.listaPlantas[i]!=null) {
					this.listaPlantas[i].mover();
					
					if(this.listaPlantas[i].getX()+this.listaPlantas[i].getAncho()/2>=this.entorno.ancho())	
						this.listaPlantas[i].setDireccion("izquierda");
						
					if(this.listaPlantas[i].getX()-this.listaPlantas[i].getAncho()/2<=0) 
						this.listaPlantas[i].setDireccion("derecha");
					
					if(this.listaPlantas[i].getY()+this.listaPlantas[i].getAlto()/2>=this.entorno.alto()-50) 
						this.listaPlantas[i].setDireccion("arriba");
						
					if(this.listaPlantas[i].getY()-this.listaPlantas[i].getAlto()/2<=0) 
						this.listaPlantas[i].setDireccion("abajo");
				}
					
			}
			
			
			
//			Disparo bola de Fuego
			if(timeBola>0)
			timeBola--;	
			for(int i= 0; i<arregloBolas.length; i++) {
				if(this.arregloBolas[i]==null) {
					if(timeBola==0) {
						Random random=new Random ();
						int numAleatorio1 = random.nextInt(4);
						arregloBolas[i]=listaPlantas[numAleatorio1].lanzarBola(listaPlantas[numAleatorio1].getDireccion());
						timeBola=200;
					}
				}
				if(this.arregloBolas[i]!=null) {
					this.arregloBolas[i].dibujar(entorno);
					this.arregloBolas[i].mover();
					if (colisionBolaInterfaz(arregloBolas[i])&& this.arregloBolas[i]!=null) {
						this.arregloBolas[i]=null;
					}
					if (colisionBolaPoncho(arregloBolas[i], this.poncho)&& this.arregloBolas[i]!=null) {
						this.vidas[this.poncho.getVidas()-1]=this.vidaNeg;
						this.poncho.perderVida();
						this.arregloBolas[i]=null;
						if(this.poncho.getVidas()==0) {
							this.poncho=null;
						}
					}
					if (colisionBolaAutito(arregloBolas[i], this.autito1) && this.autito1 != null && this.arregloBolas[i]!=null) {
						this.autito1 = null;
						this.arregloBolas[i]=null;
					}
					if (colisionBolaAutito(arregloBolas[i], this.autito2) && this.autito2 != null && this.arregloBolas[i]!=null) {
						this.autito2 = null;
						this.arregloBolas[i]=null;
					}
					if (colisionBolaAutito(arregloBolas[i], this.autito3) && this.autito3 != null && this.arregloBolas[i]!=null) {
						this.autito3 = null;
						this.arregloBolas[i]=null;
					}
					if (colisionBolaAutito(arregloBolas[i], this.autito4) && this.autito4 != null && this.arregloBolas[i]!=null) {
						this.autito4 = null;
						this.arregloBolas[i]=null;
					}
					if (colisionBolaAutito(arregloBolas[i], this.autito5) && this.autito5 != null && this.arregloBolas[i]!=null) {
						this.autito5 = null;
						this.arregloBolas[i]=null;
					}
				}
			}
	
			
//			Movimiento autos
			if (this.autito1 != null) {
				this.autito1.mover("derecha");
				if (this.autito1.getX()>this.entorno.ancho()+ 500) {
					this.autito1.setX(0);
				}
			}
			if (this.autito2 != null) {
				this.autito2.mover("derecha");
				if (this.autito2.getX()>this.entorno.ancho()+400) {
					this.autito2.setX(0);
				}
			}
			if (this.autito3 != null) {
				this.autito3.mover("izquierda");
				if (this.autito3.getX()< -200) {
					this.autito3.setX(this.entorno.ancho()+250);
				}
			}
			
			if (this.autito4 != null) {
				this.autito4.mover("abajo");
				if (this.autito4.getY()>this.entorno.alto()-20) {
					this.autito4.setY(-1800);
				}
			}
			
			if (this.autito5 != null) {
				this.autito5.mover("arriba");
				if (this.autito5.getY()<-20) {
					this.autito5.setY(this.entorno.alto()+800);
				}
			}
			
			
			
			
	//		Colision con Manzanas
			for(int i1=0; i1<listaManzanas.length;i1++) {
				if (colisionPoncho(listaManzanas[i1], this.poncho) && this.entorno.estaPresionada(this.entorno.TECLA_ARRIBA)) {
					this.poncho.moverAbajo();
				}
				
				if (colisionPoncho(listaManzanas[i1], this.poncho) && this.entorno.estaPresionada(this.entorno.TECLA_ABAJO)) {
					this.poncho.moverArriba();
				}
				
				if (colisionPoncho(listaManzanas[i1], this.poncho) && this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)) {
					this.poncho.moverIzquierda();	
				}
				
				if (colisionPoncho(listaManzanas[i1], this.poncho) && this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)) {
					this.poncho.moverDerecha ();
				}
			}
			
			
//			Colision Poncho con Manzanas y Autos (Pierde vida)
			for (int i1=0; i1<this.listaPlantas.length;i1++) {
				if(listaPlantas[i1]!=null && this.poncho!=null) {
					if(this.poncho.getVidas()==3||this.coolDown>=200) {
						if(colisionPlanta(listaPlantas[i1]) || (colisionAuto(autito1)||colisionAuto(autito2)||colisionAuto(autito3) ||colisionAuto(autito4)||colisionAuto(autito5))) {
							this.vidas[this.poncho.getVidas()-1]=this.vidaNeg;
							this.poncho.perderVida();
							if(this.poncho.getVidas()==0) {
								this.poncho=null;
							}
							this.coolDown=0;
						}
					}else {
						coolDown++;
					}
				}
			}
			
//		Condicion de victoria
		}else if(time<=0 && this.poncho != null){
			win.dibujarFinal(this.entorno,this.puntaje,this.eliminados);
			if(this.entorno.sePresiono(this.entorno.TECLA_ENTER)){
				this.menu.setAvanzar(false);
				this.time=4000;
				this.poncho=new Poncho("Imagenes/poncho.png",400,this.entorno.alto()-125,40,45,0.09);
				this.poncho.setVidas();
				Image vida =Herramientas.cargarImagen("Imagenes/hueso.png");
				vidas = new Image[this.poncho.getVidas()];
				for(int i=0; i<vidas.length;i++) {
					vidas[i]=vida;
				}
				eliminados=0;
				puntaje=0;
				if (this.autito1 == null) {
					this.autito1 = new Autitos("Imagenes/car3.png", 25,this.entorno.alto()-75,20,10,0.11,3);
					this.autito1.dibujar(this.entorno);
				}
				if (this.autito2 == null) {
					this.autito2 = new Autitos("Imagenes/car2.png", 25,this.entorno.alto()/2-50,20,10,0.11,4);
					this.autito2.dibujar(this.entorno);
				}
				if (this.autito3 == null) {
					this.autito3 = new Autitos("Imagenes/car4.png", this.entorno.ancho()-25,25,20,10,0.11,3);
					this.autito3.dibujar(this.entorno);
				}
				if (this.autito4 == null) {
					this.autito4 = new Autitos("Imagenes/car.png", 25,25,20,10,0.11,4);
					this.autito4.dibujar(this.entorno);
				}
				if (this.autito5 == null) {
					this.autito5 = new Autitos("Imagenes/car.png", this.entorno.ancho()-25,25,20,10,0.11,3);
					this.autito5.dibujar(this.entorno);
				}
				for(int i=0;i<listaPlantas.length;i++) {
					listaPlantas[i]=null;
				}
			}else if(this.entorno.sePresiono(this.entorno.TECLA_CTRL)) {
				this.entorno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.entorno.setLocationRelativeTo(null);
				this.entorno.setVisible(false);
			}
//		Condicion derrota
		}else{
//			Juego Terminado
			gameOver.dibujarFinal(this.entorno,this.puntaje,this.eliminados);
			if(this.entorno.sePresiono(this.entorno.TECLA_ENTER)){
				this.menu.setAvanzar(false);
				this.time=4000;
				this.poncho=new Poncho("Imagenes/poncho.png",400,this.entorno.alto()-125,40,45,0.09);
				this.poncho.setVidas();
				Image vida =Herramientas.cargarImagen("Imagenes/hueso.png");
				vidas = new Image[this.poncho.getVidas()];
				for(int i=0; i<vidas.length;i++) {
					vidas[i]=vida;
				}
				eliminados=0;
				puntaje=0;
				if (this.autito1 == null) {
					this.autito1 = new Autitos("Imagenes/car3.png", 25,this.entorno.alto()-75,20,10,0.11,3);
					this.autito1.dibujar(this.entorno);
				}
				if (this.autito2 == null) {
					this.autito2 = new Autitos("Imagenes/car2.png", 25,this.entorno.alto()/2-50,20,10,0.11,4);
					this.autito2.dibujar(this.entorno);
				}
				if (this.autito3 == null) {
					this.autito3 = new Autitos("Imagenes/car4.png", this.entorno.ancho()-25,25,20,10,0.11,3);
					this.autito3.dibujar(this.entorno);
				}
				if (this.autito4 == null) {
					this.autito4 = new Autitos("Imagenes/car.png", 25,25,20,10,0.11,4);
					this.autito4.dibujar(this.entorno);
				}
				if (this.autito5 == null) {
					this.autito5 = new Autitos("Imagenes/car.png", this.entorno.ancho()-25,25,20,10,0.11,3);
					this.autito5.dibujar(this.entorno);
				}
				for(int i=0;i<listaPlantas.length;i++) {
					listaPlantas[i]=null;
				}
			}else if(this.entorno.sePresiono(this.entorno.TECLA_CTRL)) {
				this.entorno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.entorno.setLocationRelativeTo(null);
				this.entorno.setVisible(false);
			}
		}
			
	}
		
	
	
	
	
//	Metodo ColisionManzanaPoncho
	private boolean colisionPoncho(Manzanas m, Poncho p){
		if(p!=null) {
			boolean colisionX=(this.poncho.getX()+this.poncho.getAncho()/2)>= m.getPoint(3).x && (this.poncho.getX()-this.poncho.getAncho()/2)<=m.getPoint(4).x;
			
			boolean colisionY=(this.poncho.getY()-this.poncho.getAlto()/2)<= m.getPoint(3).y && (this.poncho.getY()+this.poncho.getAlto()/2)>= m.getPoint(1).y;
			
			return colisionX && colisionY; 
		} 
		return false;
	}
	
//	Metodo ColisionPlantasPoncho
	public boolean colisionPlanta(Plantas p) {
			boolean colisionX=(this.poncho.getX()+this.poncho.getAncho()/2)>= p.getX()-p.getAncho()/2 && (this.poncho.getX()-this.poncho.getAncho()/2)<=p.getX()+p.getAncho()/2;
			
			boolean colisionY=(this.poncho.getY()-this.poncho.getAlto()/2)<= p.getY()+p.getAlto()/2 && (this.poncho.getY()+this.poncho.getAlto()/2)>= p.getY()-p.getAlto()/2;
			
			return colisionX && colisionY;
	}
	
//	Metodo ColisionAutosPoncho
	public boolean colisionAuto(Autitos a) {
		if (a!= null) {
			boolean colisionX=(this.poncho.getX()+this.poncho.getAncho()/2)>= a.getX()- a.getAncho()/2 && (this.poncho.getX()-this.poncho.getAncho()/2)<=a.getX()+a.getAncho()/2;
			
			boolean colisionY=(this.poncho.getY()-this.poncho.getAlto()/2)<= a.getY()+ a.getAlto()/2 && (this.poncho.getY()+this.poncho.getAlto()/2)>= a.getY()-a.getAlto()/2;
			
			return colisionX && colisionY;
		}
		return false;
	}
	
//	Metodo ColisionRayoPared
	private boolean colisionRayoPared(Rayo r){
		if(r!=null) {
			boolean colision = this.shoot.getX()>=this.entorno.ancho()||this.shoot.getX()<=0||this.shoot.getY()>=this.entorno.alto()-50||this.shoot.getY()<=0;

			return colision; 
		}
		return false;
	}	
	
//	Metodo ColisionRayoPlanta
	public boolean colisionRayoPlanta(Plantas p,Rayo r) {
		if(r!=null) {
			boolean colisionX=(this.shoot.getX()>=p.getX()-p.getAncho()/2)&&(this.shoot.getX()<=p.getX()+p.getAncho()/2);
			
			boolean colisionY=(this.shoot.getY()<=p.getY()+p.getAlto()/2)&&(this.shoot.getY()>=p.getY()-p.getAlto()/2);
			
			return colisionX && colisionY;
		}
		return false;
	}
	
//	Metodo ColisionRayoAutito
	public boolean colisionRayoAutito(Autitos a,Rayo r) {
		if(r!=null && a!= null) {
			boolean colisionX=(this.shoot.getX()>=a.getX()-a.getAncho()/2)&&(this.shoot.getX()<=a.getX()+a.getAncho()/2);
			
			boolean colisionY=(this.shoot.getY()<=a.getY()+a.getAlto()/2)&&(this.shoot.getY()>=a.getY()-a.getAlto()/2);
			
			return colisionX && colisionY;
		}
		return false;
	}
	
//	Metodo ColisionRayoManzana
	public boolean colisionRayoManzana(Manzanas m,Rayo r) {
		if(r!=null) {
			boolean colisionX=(this.shoot.getX()>=m.getX()-m.getAncho()/2)&&(this.shoot.getX()<=m.getX()+m.getAncho()/2);
			
			boolean colisionY=(this.shoot.getY()<=m.getY()+m.getAlto()/2)&&(this.shoot.getY()>=m.getY()-m.getAlto()/2);
			
			return colisionX && colisionY;
		}
		return false;
	}
	
//	Metodo ColisionBolaInterfaz
	public boolean colisionBolaInterfaz(Bola b) {
		if(b!=null) {
			boolean colision = b.getX()+b.getAncho()/2>=this.entorno.ancho()||b.getX()-b.getAncho()/2<=0||b.getY()+b.getAlto()/2>=this.entorno.alto()-50||b.getY()-b.getAlto()/2<=0;
		
			return colision;
		}
		return false;
	}
	
//	Metodo ColisionBolaPoncho
	public boolean colisionBolaPoncho(Bola b, Poncho p) {
		if (b!=null && p!= null){
			boolean colisionX=(b.getX()>=p.getX()-p.getAncho()/2)&&(b.getX()<=p.getX()+p.getAncho()/2);
			
			boolean colisionY=(b.getY()<=p.getY()+p.getAlto()/2)&&(b.getY()>=p.getY()-p.getAlto()/2);
			
			return colisionX && colisionY;
		}
		return false;
	}
	
//	Metodo ColisionBolaAutito
	public boolean colisionBolaAutito(Bola b, Autitos a) {
		if (b!=null && a!= null){
			boolean colisionX=(b.getX()+b.getAncho()/2>=a.getX()-a.getAncho()/2)&&(b.getX()-b.getAncho()/2<=a.getX()+a.getAncho()/2);
			
			boolean colisionY=(b.getY()-b.getAlto()/2<=a.getY()+a.getAlto()/2)&&(b.getY()+b.getAlto()/2>=a.getY()-a.getAlto()/2);
			
			return colisionX && colisionY;
		}
		return false;
	}
	
//	Metodo ColisionRayoBolita
	public boolean colisionRayoBolita(Bola b, Rayo r) {
		if (b!=null && r!= null){
			boolean colisionX=(r.getX()+r.getAncho()/2)>= b.getX()- b.getAncho()/2 && (r.getX()-r.getAncho()/2)<=b.getX()+b.getAncho()/2;
			
			boolean colisionY=(r.getY()-r.getAlto()/2)<= b.getY()+ b.getAlto()/2 && (r.getY()+r.getAlto()/2)>= b.getY()-b.getAlto()/2;
			
			return colisionX && colisionY;
		}
		return false;
	}
	


	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
