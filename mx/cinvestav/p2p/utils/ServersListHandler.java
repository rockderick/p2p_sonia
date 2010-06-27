package mx.cinvestav.p2p.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class ServersListHandler {

	private BufferedReader archivo = null;

	public ServersListHandler(String nombreArchivo) {
		BufferedReader superParFile;
		try {
			superParFile = new BufferedReader(new FileReader(nombreArchivo));
			archivo = superParFile;
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
			e.printStackTrace();
		}
	}

	/**
	 * Regresa la siguiente ip en el archivo (solo la ip, no el peso) 
	 * */
	public String getNextIp() {
		String ipSuperPar = "127.0.0.1";
		try {
			ipSuperPar = archivo.readLine();
			
			ipSuperPar = ipSuperPar.split("\t")[0];//se tokeniza y se toma solo el primer token
		} catch (IOException e) {
			System.out.println("No se pudo leer usando localhost");
			e.printStackTrace();
		}

		return ipSuperPar;
	}

	public Hashtable<String, Integer> getConexiones() {
		// TODO Auto-generated method stub
		return null;
	}

}
