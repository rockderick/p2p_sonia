package mx.cinvestav.p2p.comunication;

import java.net.*; 
import java.io.*;


public class RodrigoParSimpleDummy {

/**
 * Este metodo debe regresar true si se encontro el archivo false en los demas casos
 * */
	public boolean solicitar(String file) {
              String ip;
              boolean found = false;
              BufferedReader hosts=null;
              try{
                 hosts = new BufferedReader(new FileReader("servers.list"));
                 while(! found && (ip = hosts.readLine()) != null) {

                 Socket s = new Socket(ip, 1234);
                 PrintWriter srv = new PrintWriter(s.getOutputStream(), true);

                 srv.println(3 + "\n" + file);

      
                 BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
                 //System.out.println("buffer: "+br.readLine());
    
                 if(br.readLine()!=null){
                    System.out.println("entra");
                    hosts.close();
                    return true;
         
                  }  
                }
               
               //return false;
              }catch(ConnectException ce){
                    System.out.println("Could not connect: ");
                    ce.printStackTrace();
              }catch(IOException ioe){
                  ioe.printStackTrace();  
              }finally{
                 try{
                 hosts.close();
                 }catch(IOException ioe){
                     ioe.printStackTrace();  
                 }   
              } 
              return false;		
		
	}

}
