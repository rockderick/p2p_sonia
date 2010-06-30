package mx.cinvestav.p2p.fileListSincro;

import java.net.*;
import java.io.*;

/**
 * Aqui se tiene que leer los archivos que estén en el directorio actual y
 * mandarlos al server encontrado en el archivo 'servers.list'
 */
public class GironParSimpleDummy {
	Socket s;
	String ip;

	/**
	 * Este metodo debe notificar al super-par actualmente conectado que el par
	 * simple se cierra, para que borre de su lista los archivos de este par
	 * simple
	 */
	public void desconectar() {
		PrintWriter srv = null;
		try {
			s = new Socket(ip, 1232);
			srv = new PrintWriter(s.getOutputStream(), true);
			srv.println("/");
			s.close();
		} catch (IOException e) {
			System.out.println("Error al cerrar el socket");
		}
		srv.close();
	}

	/**
	 * Este metodo debe enviar al super-par la lista de los archivos locales en
	 * el directorio actual
	 * 
	 * @param ipSuperPar
	 */
	public void conectar(String ipSuperPar) {
		File f1 = new File(".");
		String l_files[] = f1.list();
		PrintWriter srv = null;
		ip = ipSuperPar;
		
		try {
			s = new Socket(ipSuperPar, 1232);
			srv = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e) {
			System.err.println("Error de E/S para la conexion con: "
					+ ipSuperPar);
		}
		for (int i = 0; i < l_files.length; i++) {
			srv.println(l_files[i]);
			System.out.println(l_files[i]);
		}
		srv.close();
	}

}
