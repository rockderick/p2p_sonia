package mx.cinvestav.p2p.bellamFord;

import java.net.*;
import java.io.*;

public class RodrigoServer {


        private GironVectores giron;
        private ClienteBellman clienteBell;
        private ServerSocket serverSocket;
        private final static int PORT=5432;

	public RodrigoServer(GironVectores giron, ClienteBellman clienteBell) {
		this.giron=giron;
		this.clienteBell=clienteBell;
	}
        
        public RodrigoServer(){
        }
	
	public void serve(){
	   try{
	      serverSocket=new ServerSocket(PORT);
	      	   
	   }catch(IOException ioe){
	        ioe.printStackTrace();
	   }
	   
	   while(true){
	       try{
	          Thread t=new Thread(new Handler(serverSocket.accept()));
	          t.start(); 
	       }catch(IOException ioe){
	          ioe.printStackTrace();
	       }
	    
	   }
	} 
	
	
      class Handler implements Runnable{
           private Socket socket;
      
        
           Handler(Socket socket){
              this.socket=socket;
            }
      
            public void run(){
               GironVectores vectors=null;
               ObjectInputStream ois=null;
               try{
                   InputStream is = socket.getInputStream(); 
                   ois = new ObjectInputStream(is);
                   vectors= (GironVectores) ois.readObject(); 
                   String ip=socket.getLocalSocketAddress().toString();
                   vectors.setIp(ip);
                }catch(UnknownHostException e){
                     e.printStackTrace();
                }catch(IOException ioe){
                     ioe.printStackTrace();
                }catch(ClassNotFoundException cnfe) {
                    cnfe.printStackTrace();
                }finally{
                   try{ 
                      ois.close();
                   }catch(IOException ioe){
                       ioe.printStackTrace();
                   }    
                }
                if(giron.cambiovector(vectors)){
                       clienteBell.mandarVectorAVecinos(PORT);
                       
                }
           
            }

}
	
	
	public static void main(String args[]){
	    RodrigoServer rockServer=new RodrigoServer();          
	    rockServer.serve(); 
	}

}




