package mx.cinvestav.p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import mx.cinvestav.p2p.comunication.RodrigoParSimpleDummy;
import mx.cinvestav.p2p.fileListSincro.GironParSimpleDummy;
import mx.cinvestav.p2p.fileTransfer.ClientTransferer;
import mx.cinvestav.p2p.utils.ServersListHandler;

public class ParSimple {

	private static final int puertoEscuchaPeticiones = 1234;// puerto en el que

	// se esta

	// escuchando peticiones
	// de archivos desde el
	// superpar

	public static void main(String[] args) {
		GironParSimpleDummy giron = new GironParSimpleDummy();
		BufferedReader lineOfText;
		ClientTransferer cliente;
		String ipSuperPar;
		ServersListHandler handler;
		handler = new ServersListHandler("servers.list");
		ipSuperPar = handler.getNextIp();// se lee la primera ip del archivo, se
		// obtiene localhost en errores

		giron.conectar(ipSuperPar);// aqui se notifica al server los archivos
		// locales

		cliente = new ClientTransferer(puertoEscuchaPeticiones);
		// aqui se inicia un hilo que escuchara peticiones del superpar

		lineOfText = new BufferedReader(new InputStreamReader(System.in));
		String textLine;
		RodrigoParSimpleDummy rodrigo = new RodrigoParSimpleDummy();
		InputStream clientInput = null;
		// Se repite hasta que se escriba 'exit', cada cadena entrada se buscara
		// en la red
		do {
			try {
				textLine = lineOfText.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
			if (textLine.equals("exit"))
				break;

			if (rodrigo.solicitar(textLine))// aqui se inicia la busqueda
			{
				// entra si se encontro el archivo
				cliente.writeToFile(textLine, clientInput);
			}
		} while (true);

		giron.desconectar();// aqui se notifica al servidor que ya no esta el
		// par activo para que borre las entradas de la
		// lista
	}

}
