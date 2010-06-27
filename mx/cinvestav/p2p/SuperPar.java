package mx.cinvestav.p2p;

import mx.cinvestav.p2p.fileListSincro.GironSuperParDummy;
import mx.cinvestav.p2p.utils.ServersListHandler;

public class SuperPar {
	private static final int puertoListasArchivos = 1234;

	public static void main(String[] args) {
		GironSuperParDummy giron;
		ServersListHandler handler;
		SuperParServer servidor;
		

		// inicializacion
		System.out.println("Servidor iniciando...");
		// Se empiesa a escuchar a los pares simples
		giron = new GironSuperParDummy(puertoListasArchivos);
		// Se crea on handler del archivo de conexiones
		handler = new ServersListHandler("servers.list");
		// Se crea el objeto que atiende las peticiones
		servidor = new SuperParServer(giron.getTabla(), handler.getConexiones());
		
		//Se comienzan a atender las peticiones
		servidor.start();
	}
}
