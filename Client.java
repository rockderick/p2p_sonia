import java.net.*; 
import java.io.*;


/*
* Usage: Client <filename>
* Lookup a file across one or more server(s), and bring it here.
* List of server IP addresses should be in local "server.list" text file
* (one IP per line)
*/
public class Client {

  public static void main(String[] args) throws Exception {

    String ip;
    boolean found = false;

    // Loop on servers list (obtained from local "servers.list" file)
    BufferedReader hosts = new BufferedReader(new FileReader("servers.list"));
    while(! found && (ip = hosts.readLine()) != null) {

      Socket s = new Socket(ip, 1234);
      PrintWriter srv = new PrintWriter(s.getOutputStream(), true);

      // Ask for requested file, max depth (calls to further servers) = 3
      srv.println(3 + "\n" + args[0]);

      // Bring back the file, if any
      File f = new File("." + File.separator + args[0]);
      FileOutputStream out = new FileOutputStream(f);
      found = (Server.copyStream(s.getInputStream(), out, true) > 0);
      out.close();
      if(! found) f.delete();

    }
    hosts.close();
  }
}
