import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import mx.cinvestav.p2p.fileTransfer.ClientTransferer;
import mx.cinvestav.p2p.fileTransfer.ServerTransferer;

public class pruebas {

	public static void main(String[] args) {
		ClientTransferer a;
		ServerTransferer b;

		try {
			if (args[0].equals("server")) {
				b = new ServerTransferer(1235);
				BufferedReader clientInput_;
				OutputStream clientOutput_;
				Socket client;
			
				ServerSocket srv = new ServerSocket(1234);

				client = srv.accept();
				clientInput_ = new BufferedReader(new InputStreamReader(client.getInputStream()));
				clientOutput_ = client.getOutputStream();
				
				String line = clientInput_.readLine();
			
				b.fileRequest(line, "127.0.0.1", clientOutput_);
//				b.copyStream(new FileInputStream(f), clientOutput_);

				clientInput_.close();
				clientOutput_.close();
				
			} else if(args[0].equals("enviar")) {
				a = new ClientTransferer(1235);
			} else {
				a = new ClientTransferer();
				Socket s = new Socket("127.0.0.1", 1234);
				PrintWriter srv = new PrintWriter(s.getOutputStream(), true);
				
				srv.println(args[1]);
				a.writeToFile(args[1], s.getInputStream());
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
