package mx.cinvestav.p2p.fileListSincro;
import java.net.*;
import java.io.*;
import java.util.StringTokenizer;
public class ArchivosLocales {

    /**
     * Metodo para reguntar si un archivo este en alguno de los pares simples conectados a este super-par
     * @param file nombre del archivo que se busca
     * @return true cuando el archivo lo tiene uno de los pares simples del super-par, false de lo contrario
     */
    synchronized public boolean isLocal(String file)
    {
        File f = new File("." + File.separator + file);
        if (f.exists()) {   
            return true;
          }else{
            return false;
        }
    }
    
    /**
     * Metodo para obtener la ip del par simple que tiene el archivo file
     * @param file archivo que se busca
     * @return la ip de algun par simple que tiene el archivo, null si no se tiene el archivo
     */
    synchronized public String getParSimpleIp(String file) throws IOException
    {
        String linea="",ip="",n_file="";
        String archivo="pares_files.list";
        BufferedReader lista;
        Boolean found=false;
        int i;
        try {
            lista = new BufferedReader(new FileReader(archivo));
            linea=lista.readLine();
            
            while(linea!=null && found != true){
              System.out.println(linea);
              StringTokenizer st = new StringTokenizer(linea);
              i=0;
               while(st.hasMoreTokens()) { 
                   String key = st.nextToken();
                   if(i==0)
                    n_file=key;
                   if(i==1)
                    ip=key;
                   i++;
                   //System.out.println("+"+key);
                }
               if((n_file.compareTo(file))==0){
                    found=true;
                }
              linea= lista.readLine();
            }
            if(found==false){
                ip="";
                System.out.println("No se encontro el archivo");
            }
            return ip;
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo de pares pares_files.list");
            return null;
        }
        /*
        File f = new File("." + File.separator + archivo);
        if (f.exists()) {   
            return ip;
        }else{
            return null;
        }
        */
    }
}
