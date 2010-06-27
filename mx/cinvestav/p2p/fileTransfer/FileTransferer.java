package mx.cinvestav.p2p.fileTransfer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class FileTransferer {

	public int copyStream(InputStream in, OutputStream out) throws IOException {
		int nbytes = 0, total = 0;
		byte[] buf = new byte[1024];
		while ((nbytes = in.read(buf)) > 0) {
			out.write(buf, 0, nbytes);
			total += nbytes;
		}
		in.close();
		return total;
	}
}
