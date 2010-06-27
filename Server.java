
import java.net.*;
import java.io.*;

public class Server implements Runnable {

	public static void main(String[] args) throws Exception {

		ServerSocket srv = new ServerSocket(1234);

		while (true) {
			Thread t = new Thread(new Server(srv.accept()));
			t.start();
		}
	}

	// Client socket in/out streams
	BufferedReader clientInput_;
	OutputStream clientOutput_;
	Socket client;

	public Server(Socket cliente) throws Exception {
		this.client = cliente;
		clientInput_ = new BufferedReader(new InputStreamReader(client
				.getInputStream()));
		clientOutput_ = client.getOutputStream();
	}

	public void run() { // Executed upon Thread's start() method call
		try {
			String nombre = System.getProperty("user.name");
			int level = 0;

			// Read "level" information
			// (max depth if further server calls are necessary)
			String line = clientInput_.readLine();
			if (line != null)
				level = Integer.parseInt(line);

			// Read the name of the requested file
			if ((line = clientInput_.readLine()) != null) {
				File f = new File("." + File.separator + line);
				System.out.print("Client " + nombre + "/"
						+ client.getInetAddress().getHostName()
						+ " request for file " + line + "...");
				if (f.exists()) {
					copyStream(new FileInputStream(f), clientOutput_, true);
					System.out.println(" transfer done.");
				} else if (level > 0) { // File is not here... maybe on another
										// server ?
					System.out.println(" file is not here, lookup further...");
					// Lookup on other known servers (decrement depth)
					boolean found = lookupFurther(level - 1, line,
							clientOutput_);
					System.out.println(found ? "Transfer done."
							: "File not found.");
				}
			}

			clientInput_.close();
			clientOutput_.close();

		} catch (Exception e) {
		} // ignore
	}

	/*
	 * Lookup the requested file on every known server Server list is in local
	 * "servers.list" text file (one IP address per line)
	 */
	static boolean lookupFurther(int level, String fname, OutputStream out)
			throws IOException {

		System.out.println("Level = " + level);

		BufferedReader hosts;
		try {
			hosts = new BufferedReader(new FileReader("servers.list"));
		} catch (FileNotFoundException e) {
			System.out.println("No servers.list file, can't lookup further !");
			return false;
		}

		String ip;
		boolean found = false;
		while (!found && (ip = hosts.readLine()) != null) {
			System.out.println("trying server " + ip);
			try {
				Socket s = new Socket(ip, 1234);
				PrintWriter srv = new PrintWriter(s.getOutputStream(), true);
				srv.println(level + "\n" + fname);
				int nbytes = copyStream(s.getInputStream(), out, true);
				s.close();
				found = (nbytes > 0);
			} catch (ConnectException e) {
			} // ignore
		}
		hosts.close();
		return found;
	}

	public static int copyStream(InputStream in, OutputStream out, boolean close)
			throws IOException {
		int nbytes = 0, total = 0;
		byte[] buf = new byte[1024];
		while ((nbytes = in.read(buf)) > 0) {
			out.write(buf, 0, nbytes);
			total += nbytes;
		}
		if (close)
			in.close();
		return total;
	}
}
