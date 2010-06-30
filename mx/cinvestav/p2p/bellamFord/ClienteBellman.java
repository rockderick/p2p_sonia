package mx.cinvestav.p2p.bellamFord;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Hashtable;
import java.util.Set;

public class ClienteBellman {
	private Hashtable<String, Integer> tabla;
	private GironVectores giron;

	public ClienteBellman(GironVectores giron) {
		tabla = giron.actual;
		this.giron = giron;
	}

	public void mandarVectorAVecinos(int puerto) {
		HiloMandante hilo;
		Set<String> enume = tabla.keySet();
		for (String siguiente : enume) {
			hilo = new HiloMandante(puerto, siguiente, giron);
			(new Thread(hilo)).start();
		}
	}

	public class HiloMandante implements Runnable {
		private int puerto;
		private String ip;
		private GironVectores giron;

		public HiloMandante(int puerto, String siguiente, GironVectores giron) {
			this.puerto = puerto;
			ip = siguiente;
			this.giron = giron;
		}

		public void run() {
			try {
				Socket s = new Socket(ip, puerto);
				OutputStream output = s.getOutputStream();
				ObjectOutputStream objOut = new ObjectOutputStream(output);
				objOut.writeObject(giron);
				objOut.close();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
