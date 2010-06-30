package mx.cinvestav.p2p.fileListSincro;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class ArchivosLocales {

	//archivo - ip
	private Hashtable<String, String> tablaAtchivos = new Hashtable<String, String>();

	/**
	 * Metodo para reguntar si un archivo este en alguno de los pares simples
	 * conectados a este super-par
	 * 
	 * @param file
	 *            nombre del archivo que se busca
	 * @return true cuando el archivo lo tiene uno de los pares simples del
	 *         super-par, false de lo contrario
	 */
	synchronized public boolean isLocal(String file) {
//		boolean fla = false;
//		Collection<String> iterable = tablaAtchivos.keySet();
//		for (String siguiente : iterable) {
//			if (siguiente.equals(file)) {
//				fla = true;
//				break;
//			}
//		}
		return tablaAtchivos.containsKey(file);
	}

	/**
	 * Metodo para obtener la ip del par simple que tiene el archivo file
	 * 
	 * @param file
	 *            archivo que se busca
	 * @return la ip de algun par simple que tiene el archivo, null si no se
	 *         tiene el archivo
	 */
	synchronized public String getParSimpleIp(String file){
		return tablaAtchivos.get(file);
		}
	
	synchronized void borrar(String ip)
	{
		ArrayList<String> listaBorrar = new ArrayList<String>();
		Set<String> llaves = tablaAtchivos.keySet();
		for (String siguiente : llaves) {
			if (tablaAtchivos.get(siguiente).equals(ip)) 
				listaBorrar.add(siguiente);
			}
		for (String siguiente : listaBorrar) {
			tablaAtchivos.remove(siguiente);
			System.out.println("Borrado:" + siguiente);
		}
			
	}
	
	synchronized void agregar(String archivo, String ip)
	{
		tablaAtchivos.put(archivo, ip);
	}
}
