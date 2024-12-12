package presentador;

import java.awt.Point;
import java.util.List;
import interfazVisual.Pieza;
import modelo.Modelo;

public class Presentador {
	
	private static Modelo modelo;
	private static int dimension;
	private static int tamanio;
	
	//Constructor de Presentador
	public Presentador(int cantMovimientos, int dimension, int tamanio, String nombreJugador) {
		this.modelo = new Modelo(cantMovimientos, dimension, nombreJugador);
		this.dimension = dimension;
		this.tamanio = tamanio;
	}

	//Metodo para solicitar a modelo matriz de enteros para armar matriz de Pieza
	public Pieza[][] solicitarTablero() {
		int[][] esqueletoTablero = modelo.crearTablero(dimension, tamanio);
		Pieza [][] tablero = new Pieza[dimension][dimension];
		return transicionEsqueletoATablero(esqueletoTablero, tablero);
		
    }
		
	//Metodo para crear Pieza en base a casillero de matriz
	public Pieza[][] transicionEsqueletoATablero (int[][]esqueletoTablero, Pieza[][] tablero){
		int tamanioPieza = tamanio / dimension;
		for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
            	if (esqueletoTablero[i][j] == 0) {
	            	tablero[i][j] = new Pieza(true, "", tamanioPieza, j, i);
	            }
            	else {
            		tablero[i][j] = new Pieza(false, String.valueOf(esqueletoTablero[i][j]), tamanioPieza, j, i);
            	}
            }
		}
		return tablero;
	}

	//Metodo para solicitar a modelo que desordene la matriz ordenada inicial (se crea nueva matriz de Pieza)
	public Pieza[][] desordenarTablero(int cantidadVecesADesordenarTablero) {
		int [][] esqueletoDesordenado = modelo.desordenarTablero(cantidadVecesADesordenarTablero);
		Pieza [][] tablero = new Pieza[dimension][dimension];
		return transicionEsqueletoATablero(esqueletoDesordenado, tablero);
	}

	//Metodo que solicita a modelo validar si el jugador gano el juego
	public boolean comprobarGano() {
		return modelo.comprobarGano();
	}

	//Metodo que solicita a modelo validar si el jugador perdio el juego
	public boolean comprobarPerdio() {
		return modelo.comprobarPerdio();
	}

	////Metodo que solicita a modelo validar si el jugador puede mover la pieza seleccionada
	public Pieza[][] intentarMoverPieza(Point pActual) {
	    int[][] esqueletoIntercambio =  modelo.intentarMoverPieza(pActual);
	    Pieza [][] tablero = new Pieza[dimension][dimension];
		return transicionEsqueletoATablero(esqueletoIntercambio, tablero);
	}

	////Metodo que solicita a modelo restar movimientos restantes
	public void restarMovimiento() {
		this.modelo.restarMovimiento();
	}
	
	//Metodo que solicita a modelo validar si una pieza pudo o no pudo moverse (relacion con vacio)
	public boolean piezaSeMovio() {
		return modelo.isMovimientoRegistrado();
	}
	
	//GETTER
	public int solicitarCantidadMovimientos() {
		return modelo.getCantMovimientos();
	}
	
	
	//METODOS PARA MANEJO DE PUNTAJE //
	
	public void registrarPuntajeGanador(String filePath, int segundos) {
		modelo.registrarPuntajeGanador(filePath, segundos);
	}
	
	public List<String> obtenerRankingCompleto(String filePath){
		return modelo.obtenerRankingCompleto(filePath);
	}
}
