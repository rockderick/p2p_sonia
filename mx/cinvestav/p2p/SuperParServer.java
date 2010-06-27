package mx.cinvestav.p2p;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

import mx.cinvestav.p2p.comunication.RodrigoSuperParDummy;
import mx.cinvestav.p2p.fileListSincro.ArchivosLocales;

/**
 * Clase con un ciclo que atiende las peticiones creando hilos para cada una
 * @author absol
 */
public class SuperParServer {
	private static final int puertoTransferencias = 1234;
	private RodrigoSuperParDummy rodrigo;
	
	public SuperParServer(ArchivosLocales archivos,
			Hashtable<String, Integer> conexiones) {
		rodrigo = new RodrigoSuperParDummy(archivos, conexiones);
	}

	public void start() {
		ServerSocket srv;
		try {
			srv = new ServerSocket(puertoTransferencias);
		} catch (IOException e) {
			System.out.println("No se pudo abrir el socket");
			e.printStackTrace();
			return;
		}

		Socket client;
		System.out.println("Atendiendo peticiones");
		while(true)
		{
			try {
				client = srv.accept();
				//debe regresar lo antes posible para poder atender mas peticiones
				rodrigo.atenderPeticion(client);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
