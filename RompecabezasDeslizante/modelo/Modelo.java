package modelo;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Modelo {
	
	private int cantMovimientos;
	private int dimension;
	private int[][] tablero;
	private Point coordenadasVacio;
	private boolean movimientoRegistrado;
	private String nombreJugador;
	
	//Constructor de Modelo
	public Modelo(int cantMovimientos, int dimension, String nombreJugador) {
		this.cantMovimientos=cantMovimientos;
		this.dimension = dimension;
		this.tablero = new int[dimension][dimension];
		this.coordenadasVacio = new Point(dimension - 1, dimension - 1);
		this.movimientoRegistrado = false;
		this.nombreJugador = nombreJugador;
	}
	
	// METODOS //
	
	//Metodo para crear matriz de enteros (esqueleto de tablero a mostrar en interfaz visual)
    public int[][] crearTablero(int dimension, int tamanio) {
        Integer contador = 1;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (contador == (dimension * dimension)) {
                    this.tablero[i][j] = 0;
                } else {
                    this.tablero[i][j] = contador;
                }
                contador++;
            }
        }
        return this.tablero;
    }

    //Metodo para desordenar matriz creada en el anterior metodo crearTablero(int dimension, int tamanio)
    public int[][] desordenarTablero(int cantidadVecesADesordenarTablero) {
    	
    	//Se elije una direccion aleatoria y se determina si se puede mover pieza para desordenar matriz
        Random random = new Random();
        for (int i = 0; i < cantidadVecesADesordenarTablero; i++) {
            int direccion = random.nextInt(4);

            // ARRIBA
            if (direccion == 0 && coordenadasVacio.y - 1 >= 0) {
                intercambiarPosiciones(coordenadasVacio, new Point(coordenadasVacio.x, coordenadasVacio.y - 1));
                coordenadasVacio.y = coordenadasVacio.y - 1;
                continue;
            }
            // ABAJO
            if (direccion == 1 && coordenadasVacio.y + 1 < dimension) {
            	intercambiarPosiciones(coordenadasVacio, new Point(coordenadasVacio.x, coordenadasVacio.y + 1));
                coordenadasVacio.y = coordenadasVacio.y + 1;
                continue;
            }
            // DERECHA
            if (direccion == 2 && coordenadasVacio.x + 1 < dimension) {
            	intercambiarPosiciones(coordenadasVacio, new Point(coordenadasVacio.x + 1, coordenadasVacio.y));
                coordenadasVacio.x = coordenadasVacio.x + 1;
                continue;
            }
            // IZQUIERDA
            if (direccion == 3 && coordenadasVacio.x - 1 >= 0) {
            	intercambiarPosiciones(coordenadasVacio, new Point(coordenadasVacio.x - 1, coordenadasVacio.y));
                coordenadasVacio.x = coordenadasVacio.x - 1;
                continue;
            }
        }
        return this.tablero;
    }
    
    //Metodo para parsear matriz a String
    public static String matrizToString(int[][] matriz) {
        return Arrays.deepToString(matriz);
    }
    
    
    //Metodo que intercambia posicion entre dos piezas
    private void intercambiarPosiciones(Point p1, Point p2) {
        int temp = tablero[p1.y][p1.x];
        this.tablero[p1.y][p1.x] = tablero[p2.y][p2.x];
        this.tablero[p2.y][p2.x] = temp;
    }
    
    //Metodo que valida si es posible o no mover una pieza en relacion con pieza vacia
    public int[][] intentarMoverPieza(Point pActual) {
        Point pVacio = null;

        // Se verifica si puede moverse a la posición arriba
        if (pActual.y > 0 && this.tablero[pActual.y-1][pActual.x] == 0) {
            pVacio = new Point(pActual.x, pActual.y-1);
        }
        // Se verifica si puede moverse a la posición abajo
        else if (pActual.y < dimension - 1 && this.tablero[pActual.y+1][pActual.x] == 0) {
            pVacio = new Point(pActual.x, pActual.y+1);
        }
        // Se verifica si puede moverse a la izquierda
        else if (pActual.x > 0 && this.tablero[pActual.y][pActual.x-1] == 0) {
            pVacio = new Point(pActual.x-1, pActual.y);
        }
        // Se verifica si puede moverse a la derecha
        else if (pActual.x < dimension - 1 && this.tablero[pActual.y][pActual.x+1] == 0) {
            pVacio = new Point(pActual.x+1, pActual.y);
        }

        // Si se encuentra una pieza vacía en una dirección válida, se realiza el intercambio
        if (pVacio != null) {
            intercambiarPosiciones(pActual, pVacio);
            this.movimientoRegistrado = true;
            return this.tablero; // El movimiento fue exitoso
        }
        this.movimientoRegistrado = false;
        return this.tablero; // No se pudo mover
    }
    
    
    //Metodo para validar si el jugador gano el juego
    public boolean comprobarGano() {
        ArrayList<Integer> numeros =  new ArrayList<Integer>();
        
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
            	int actual = this.tablero[i][j];
            	if(actual != 0) {
            		numeros.add(actual);
            	}
            }
        }

       Integer numActual = numeros.get(0);
       for (int i = 1; i < numeros.size(); i++) {
    	   if(numActual>=numeros.get(i)) {
    		   return false;
    	   }else {
    		   numActual = numeros.get(i);
    	   }
       }
       return true;
    }
    
    //Metodo para validar si el jugador perdio (sin movimientos restantes disponibles)
	public boolean comprobarPerdio() {
		return this.cantMovimientos<=0;
	}

	//Metodo para disminuir movimientos
	public void restarMovimiento() {
		this.cantMovimientos--;
	}
	
	//MANEJO DE ARCHIVO PUNTAJE //
	
	//Metodo principal para manejo de puntaje
	public void registrarPuntajeGanador(String filePath, int segundos) {
		escribirPuntajeEnArchivo(filePath, segundos);
		List<String> puntajesJugadores = leerYOrdenarPuntajes(filePath);
		escribirPuntajesOrdenados(filePath, puntajesJugadores);
	}
	
	//Funcion para escribir el puntaje en el archivo
	public void escribirPuntajeEnArchivo(String filePath, int segundos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(nombreJugador + " - " + segundos + " segundos");
            writer.newLine();
            writer.flush();  
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	//Metodo para leer contenido de archivo puntaje y ordenarlos de menor a mayor
	public List<String> leerYOrdenarPuntajes(String filePath) {
	    List<String> listaJugadores = new ArrayList<>();

	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String linea;

	        // Leo linea por linea de archivo
	        while ((linea = reader.readLine()) != null) {
	            //Contemplo caso de que el archivo se encuentre vacio
	        	if (linea.trim().isEmpty()) {
	                continue; 
	            }

	            String[] partes = linea.split(" - ");
	            if (partes.length < 2) {
	                continue; 
	            }
	            
	            String nombre = partes[0].trim();
	            
	            // Se extrae solo el valor numérico de los puntos
	            try {
	                int puntos = Integer.parseInt(partes[1].replace(" segundos", "").trim());
	                listaJugadores.add(nombre + " - " + puntos + " segundos");
	            } catch (NumberFormatException e) {
	                continue; 
	            }
	        }
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }

	    // Si la lista está vacía, se retorna sin ordenar
	    if (listaJugadores.isEmpty()) {
	        return listaJugadores;
	    }

	    // Se ordena la lista por puntos de menor a mayor
	    listaJugadores.sort(Comparator.comparingInt(linea -> {
	        String[] partes = linea.split(" - ");
	        return Integer.parseInt(partes[1].replace(" segundos", "").trim());
	    }));

	    return listaJugadores;
	}

    
    // Función para escribir los puntajes ordenados en el archivo
    public void escribirPuntajesOrdenados(String filePath, List<String> listaJugadores) {
        // Si la lista está vacía, no hacemos nada
        if (listaJugadores == null || listaJugadores.isEmpty()) {
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Escribir cada jugador y sus puntos en el archivo (solo hasta 5 jugadores si hay)
            for (String jugador : listaJugadores.subList(0, Math.min(5, listaJugadores.size()))) {
                writer.write(jugador);
                writer.newLine(); 
            }
            writer.flush();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para listar contenido de archivo puntaje
    public List<String> obtenerRankingCompleto(String filePath) {
        List<String> listaMejoresPuntajes = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linea;

            // Leer cada línea del archivo
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    listaMejoresPuntajes.add(linea); // Solo añadir líneas no vacías
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        return listaMejoresPuntajes;
    }


	//GETTERS/SETTERS
	
	public int getCantMovimientos() {
		return cantMovimientos;
	}

	public void setCantMovimientos(int cantMovimientos) {
		this.cantMovimientos = cantMovimientos;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public int[][] getTablero() {
		return tablero;
	}

	public void setTablero(int[][] tablero) {
		this.tablero = tablero;
	}

	public Point getCoordenadasVacio() {
		return coordenadasVacio;
	}

	public void setCoordenadasVacio(Point coordenadasVacio) {
		this.coordenadasVacio = coordenadasVacio;
	}

	public boolean isMovimientoRegistrado() {
		return movimientoRegistrado;
	}

	public void setMovimientoRegistrado(boolean movimientoRegistrado) {
		this.movimientoRegistrado = movimientoRegistrado;
	}
}
