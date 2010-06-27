package mx.cinvestav.p2p.comunication;

import java.net.Socket;
import java.util.Hashtable;

import mx.cinvestav.p2p.bellamFord.ShortestPath;
import mx.cinvestav.p2p.fileListSincro.ArchivosLocales;

public class RodrigoSuperParDummy {

	private ShortestPath bellman;
	ArchivosLocales archivos;
	
	/**
	 * Contructor con objetos necesarios
	 * @param archivosLocales Objeto de giron que debe decirte si un archivo esta en uno de los pares simples de este super par (si no continuar de acuerdo a lo que regrese ShortestPath)
	 * @param conexiones Tiene las ip de los nodos alcansables y su peso, sirve para ShortesPath
	 */
	public RodrigoSuperParDummy(ArchivosLocales archivosLocales,
			Hashtable<String, Integer> conexiones) {
		bellman = new ShortestPath(conexiones);
		archivos = archivosLocales;
	}

	/**
	 * Este metodo debe crear un hilo que atienda la peticion (correrlo) y terminar para que se regrese el contro al ciclo de atender peticiones
	 * @param client socket abierto con la peticion
	 */
	public void atenderPeticion(Socket client) {
		// pseudo codigo de lo que debe hacer masomenos
		
		/*
		 * 1 leer una linea del socket para saber el nombre de archivo que se esta buscando
		 * 2 hacer archivos.isLocal(file);
		 * 		si es local usar un ServerTransfere para obtener el flujo del archivo y mandarlo por el socket
		 * 		sino hacer bellman.restartOrder() para reiniciar la secuencia de caminos
		 * 			mientras que no se acaben los caminos (null)
		 * 				hacer bellman.nextNode()
		 * 				mandar la peticion de busqueda al nodo con la ip regresada
		 * 				si se se encontro el archivo mandar el flujo de regreso por el socket
		 * 			fin mintras
		 * 		fin si
		 * 3 si no se encontro notificarlo por el socket
		 * */
	}

}
