package mx.cinvestav.p2p.fileTransfer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ClientTransferer extends FileTransferer implements Runnable {
	private ServerSocket socket;
/**
 * @author absol
 * Clase para atender una peticion particular
 */


	/**
	 * @author absol
	 * Una nueva peticion para atenderse
	 * @param soc Socket en el que se atendera la peticion
	 */
	public void peticion(Socket soc) {
		try {
			BufferedReader fileRequest = new BufferedReader(
					new InputStreamReader(soc.getInputStream()));
			String fileName = fileRequest.readLine();
			OutputStream out = soc.getOutputStream();
			readFileTo(fileName, out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author absol
	 * iniciar un hilo para atender una peticion
	 */
	public void run() {
		while (true) {
			try {
				Socket soc = socket.accept();
				EnviadorArchs env = new EnviadorArchs(soc, this);
				Thread t = new Thread(env);
				t.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public ClientTransferer(int puerto) {
		try {
			socket = new ServerSocket(puerto);

			Thread t = new Thread(this);
			t.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ClientTransferer() {
		// TODO borrar este metodo despues de las pruebas
	}

	public int readFileTo(String fileName, OutputStream clientOutput)
			throws FileNotFoundException, IOException {
		File f = new File("." + File.separator + fileName);
		copyStream(new FileInputStream(f), clientOutput);
		return 0;
	}

	public int writeToFile(String fileName, InputStream clientInput) {
		int found = 0;
		File f = new File("." + File.separator + "temp");
		try {
			FileOutputStream out = new FileOutputStream(f);
			found = copyStream(clientInput, out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al escribir");
			e.printStackTrace();
		}
		if (found > 0) {
			File dest;
			dest = new File("." + File.separator + fileName);
			f.renameTo(dest);
		} else
			f.delete();
		return found;
	}
}
