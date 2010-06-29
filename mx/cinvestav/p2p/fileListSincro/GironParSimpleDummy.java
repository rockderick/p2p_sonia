package mx.cinvestav.p2p.fileListSincro;
import java.net.*;
import java.io.*;
/**
 *         Aqui se tiene que leer los archivos que est√©n en el directorio actual
 *         y mandarlos al server encontrado en el archivo 'servers.list'
 */
public class GironParSimpleDummy {
    Socket s;   
    /**
     * Este metodo debe notificar al super-par actualmente conectado que el par
     * simple se cierra, para que borre de su lista los archivos de este par
     * simple
     */
    public void desconectar() {
        // TODO Auto-generated method stub
        try{
            s.close();
        }catch(IOException e)
        {
            System.out.println("Error al cerrar el socket");
        }
    }

    /**
     * Este metodo debe enviar al super-par la lista de los archivos locales en
     * el directorio actual
     * @param ipSuperPar
     */
    public void conectar(String ipSuperPar) throws IOException{
        DataOutputStream salida= null;
        File f1 = new File(".");
        String l_files[]= f1.list();
        try{
            s= new Socket(ipSuperPar,1232);
            salida = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) { 
            System.err.println("Error de E/S para la conexión con: " + ipSuperPar);
        }
        
            for (int i=0;i<l_files.length;i++){
                salida.writeBytes(l_files[i]);
                salida.writeByte('\n');
                System.out.println(l_files[i]);
            }
        
    }
    
    
}
