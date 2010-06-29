package mx.cinvestav.p2p.fileListSincro;
import java.net.*;
import java.io.*;
public class GironSuperParDummy {
    /**
     * Lado del servidor que debe manterner las listas de archivos por par simple
     */

/**
 * El constructor debe dejar un hilo corriendo que escucha a los pares simples y actualiza las listas acorde
 * @param puertolistasarchivos Puerto en el que escuchara nuevas conexiones (y las desconexiones) de pares simples
 */
    public GironSuperParDummy(int puertolistasarchivos){
        ServerSocket srv=null;
        String msg="",ip_client="";
        String archivo="pares_files.list";
        try{
         srv= new ServerSocket(puertolistasarchivos);
        }catch (IOException e) {
            System.out.println("No se pudo abrir el socket");
            e.printStackTrace();
        }
       Socket client;
       System.out.println("Iniciando peticiones de pares");
       while(true)
       {
           try{
                client= srv.accept();
                ip_client=client.getInetAddress().getHostAddress(); 
                InputStream inputStream = client.getInputStream();
                DataInputStream flujo = new DataInputStream( inputStream ); 
                msg=flujo.readUTF();
                System.out.println("msg: "+msg);
                System.out.println("ip_cliente: "+ ip_client);
                BufferedWriter lista;
                try{
                    lista = new BufferedWriter(new FileWriter(archivo));
                    lista.write(ip_client+ " "+msg);
                }catch (FileNotFoundException e) {
                    System.out.println("No se encuentra el archivo de pares pares_files.list");
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
  public ArchivosLocales getTabla() {
    // TODO Auto-generated method stub
    return null;
   }

}
