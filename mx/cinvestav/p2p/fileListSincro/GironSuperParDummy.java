package mx.cinvestav.p2p.fileListSincro;

import java.net.*;
import java.io.*;

public class GironSuperParDummy {
	/**
	 * Lado del servidor que debe manterner las listas de archivos por par
	 * simple
	 */

	private ArchivosLocales tabla = null;

	/**
	 * El constructor debe dejar un hilo corriendo que escucha a los pares
	 * simples y actualiza las listas acorde
	 * 
	 * @param puertolistasarchivos
	 *            Puerto en el que escuchara nuevas conexiones (y las
	 *            desconexiones) de pares simples
	 */
	public GironSuperParDummy(int puertolistasarchivos) {
		ServerSocket srv = null;
		tabla = new ArchivosLocales();
		String msg = "";
		String ipClient = "";

		try {
			srv = new ServerSocket(puertolistasarchivos);
		} catch (IOException e) {
			System.out.println("No se pudo abrir el socket");
			e.printStackTrace();
		}
		Socket client;
		System.out.println("Iniciando peticiones de pares");
		while (true) {
			try {
				client = srv.accept();
				ipClient = client.getInetAddress().getHostAddress();
				InputStream inputStream = client.getInputStream();
				BufferedReader flujo = new BufferedReader(
						new InputStreamReader(inputStream));
				msg = flujo.readLine();
				if (!msg.equals("/")) {
					System.out.println("recibiendo lista de archivos desde: "
							+ ipClient);
					System.out.println("archivo: " + msg);
					tabla.agregar(msg, ipClient);
					msg = flujo.readLine();
					while (msg != null) {
						tabla.agregar(msg, ipClient);
						System.out.println("archivo: " + msg);
						msg = flujo.readLine();
					}
				} else {
					System.out.println("Desconectandose: " + ipClient);
					tabla.borrar(ipClient);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public ArchivosLocales getTabla() {
		return tabla;
	}

}
