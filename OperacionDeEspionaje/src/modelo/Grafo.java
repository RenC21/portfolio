package modelo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Grafo
{
    private double[][] grafo ;

    public Grafo(int vertices)
    {
        grafo = new double[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                grafo[i][j] = -1;
            }
        }
    }
    
    public boolean esContiguo() {
        boolean[] visitado = new boolean[tamano()];
        int verticeInicial = 0; 

        bfs(verticeInicial, visitado);

        for (boolean v : visitado) {
            if (!v) {
                return false;
            }
        }
        return true; 
    }

    private void bfs(int vertice, boolean[] visitado) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertice);
        visitado[vertice] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll(); 

            for (int vecino : vecinos(v)) {
                if (!visitado[vecino]) {
                    visitado[vecino] = true; 
                    queue.add(vecino); 
                }
            }
        }
    }

    public void agregarArista(int i, int j, double probabilidad)
    {
        verificarVertice(i); 
        verificarVertice(j); 
        verificarDistintos(i, j);

        grafo[i][j] = probabilidad;
        grafo[j][i] = probabilidad;
    }

    public void eliminarArista(int i, int j)
    {
        verificarVertice(i);
        verificarVertice(j);
        verificarDistintos(i, j);

        grafo[i][j] = -1;
        grafo[j][i] = -1;
    }

    public boolean existeArista(int i, int j)
    {
        verificarVertice(i);
        verificarVertice(j);
        verificarDistintos(i, j);

        return grafo[i][j] != -1;
    }

    public double getPesoArista(int i, int j)
    {
        verificarVertice(i);
        verificarVertice(j);
        return grafo[i][j];
    }

    public int tamano()
    {
        return grafo.length;
    }


    public Set<Integer> vecinos(int i)
    {
        verificarVertice(i);

        Set<Integer> ret = new HashSet<>();
        for(int j = 0; j < this.tamano(); ++j)
        {
            if( i != j && this.existeArista(i,j) )
                ret.add(j);
        }

        return ret;
    }

    private void verificarVertice(int vertice) {
        if (vertice < 0) {
            throw new IllegalArgumentException("El vértice no puede ser menor que 1: " + vertice);
        }
        if (vertice >= grafo.length) {
            throw new IllegalArgumentException("Los vértices deben estar entre 1 y " + grafo.length);
        }
    }

    private void verificarDistintos(int i, int j)
    {
        if( i == j )
            throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
    }


 // Algoritmo de Prim
    public Grafo GenerarArbolGeneradorMinimoPrim() {
        Grafo arbol = new Grafo(this.tamano());
        Set<Integer> visitados = new HashSet<>();
        List<Arista> aristas = new ArrayList<>();
        visitados.add(0);
        agregarAristasDeVertice(0, visitados, aristas);

        while (visitados.size() < this.tamano()) {
            Arista aristaMinima = null;
            for (Arista arista : aristas) {
                if (!visitados.contains(arista.destino)) {
                    if (aristaMinima == null || arista.peso < aristaMinima.peso) {
                        aristaMinima = arista;
                    }
                }
            }

            if (aristaMinima == null) break;

            visitados.add(aristaMinima.destino);
            arbol.agregarArista(aristaMinima.origen, aristaMinima.destino, aristaMinima.peso);
            agregarAristasDeVertice(aristaMinima.destino, visitados, aristas);
        }
        return arbol;
    }
    

    private void agregarAristasDeVertice(int vertice, Set<Integer> visitados, List<Arista> aristas) {
        for (int i = 0; i < this.tamano(); i++) {
        	if (vertice != i) {
        		if (this.existeArista(vertice, i) && !visitados.contains(i)) {
                    aristas.add(new Arista(vertice, i, this.getPesoArista(vertice, i)));
                }
        	}
        }
    }
}

