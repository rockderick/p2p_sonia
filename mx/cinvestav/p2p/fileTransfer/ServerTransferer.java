package mx.cinvestav.p2p.fileTransfer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;



public class ServerTransferer extends FileTransferer {

	private int port;
	
	public ServerTransferer (int puerto)
	{
		port = puerto;
	}
	
	public int fileRequest(String fileName, String ip, OutputStream out)
	{
		int total = 0;
		
		try {
			Socket s = new Socket(ip, port);
			PrintWriter srv = new PrintWriter(s.getOutputStream(), true);
			srv.println(fileName);
			total = copyStream(s.getInputStream(), out);                        
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return total;
	}
}
