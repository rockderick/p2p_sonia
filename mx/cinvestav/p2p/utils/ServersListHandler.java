package mx.cinvestav.p2p.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class ServersListHandler {

	private BufferedReader archivo = null;
	private Hashtable<String, Integer> tabla = null;
	private int contador = 0;

	public ServersListHandler(String nombreArchivo) {
		BufferedReader superParFile;

		tabla = new Hashtable<String, Integer>();
		try {
			superParFile = new BufferedReader(new FileReader(nombreArchivo));
			archivo = superParFile;
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
			tabla.put("127.0.0.1", new Integer(0));
			contador = 0;
			// e.printStackTrace();
		}
	}

	/**
	 * Regresa la siguiente ip en el archivo (solo la ip, no el peso)
	 * */
	public String getNextIp() {
		String ipSuperPar = null;
		String tmps[];

		if (archivo == null)
			return "127.0.0.1";

		try {
			ipSuperPar = archivo.readLine();
		} catch (IOException e) {
			System.out.println("No se pudo leer usando localhost");
			e.printStackTrace();
		}

		if (ipSuperPar != null) {
			tmps = ipSuperPar.split("\t");
			tabla.put(tmps[0], new Integer(Integer.parseInt(tmps[1])));
			contador++;
			return tmps[0];
		} else {
			return null;
		}
	}

	public Hashtable<String, Integer> getConexiones() {
		if (archivo != null) {
			String tmps[];

			try {
				String par = archivo.readLine();
				while (par != null) {
					tmps = par.split("\t");
					tabla.put(tmps[0], new Integer(Integer.parseInt(tmps[1])));
					contador++;

					par = archivo.readLine();
				}
			} catch (IOException e) {
				System.out.println("No se pudo leer archivo");
				e.printStackTrace();
			}
		}

		return tabla;
	}

}
