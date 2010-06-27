package mx.cinvestav.p2p.fileTransfer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class EnviadorArchs implements Runnable {
	private Socket sock;
	private ClientTransferer cliente;
	/**
	 * @author absol
	 * Sirve para atender peticiones en un hilo
	 */

	public EnviadorArchs(Socket soc, ClientTransferer clientTransferer) {
		sock = soc;
		cliente = clientTransferer;
	}

	/**
	 * @author absol
	 * El hilo se queda enviando una archiv
	 */
	public void run() {
		BufferedReader fileRequest;
		try {
			fileRequest = new BufferedReader(new InputStreamReader(sock
					.getInputStream()));
			String fileName = fileRequest.readLine();
			OutputStream out = sock.getOutputStream();
			cliente.readFileTo(fileName, out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
