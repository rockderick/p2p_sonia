package mx.cinvestav.p2p.bellamFord;

import java.util.Hashtable;

/**
 * Implementa el algoritmo de Bellman-Ford
 * @author absol
 */
public class ShortestPath {

	/**
	 * Constructor para crear un objeto inicializado
	 * @param conexiones tabla con las ips de nodos vecions y su peso
	 */
	public ShortestPath(Hashtable<String, Integer> conexiones) {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Regresa la ip para seguir el camino mas corto, cada vez que se llama regresa otra ip con un camino igual o menos corto que el anterior y cuando no hay mas caminos regresa un null
	 * @return regresa la ip del siguiente nodo a revisar con el orden de caminos mas cortos a caminos mas largos, null si ya no sigue ninguno
	 */
	public String nextNode()
	{
		return null;
	}
	
	/**
	 * Reinicia la secuencia de caminos a seguir
	 */
	public void restart()
	{
		
	}
}
