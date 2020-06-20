/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.serversocket;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author italo
 */
public class StudentsManager implements Runnable{
    
     private ConcurrentLinkedQueue<String> games;  
     private Socket clientSocket;
    @Override
    public void run() {
        System.out.println("The student enter in the room");
        while(true){
            if(RoundMannager.getInstance().isIsRoomAvaliable()){
                games = RoundMannager.getInstance().getGames();  
                
                //Now i need to figure out on how to pass the screens 
            
            }
        
        
        }
    }

    /**
     * @return the clientSocket
     */
    public Socket getClientSocket() {
        return clientSocket;
    }
    
    
      void show_yourself() {
        if ((clientSocket.isConnected()) && (!clientSocket.isClosed())) {
            System.out.println(clientSocket.hashCode() + ": Conexão cliente estabelecida.");
        } else {
            System.out.println(clientSocket.hashCode() + ": Conexão cliente encerrada.");
        }
    }

    /**
     * @param clientSocket the clientSocket to set
     */
    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    
     public void finish() throws IOException {
        if (clientSocket.isConnected()) {
            clientSocket.close();
        }
    }
    
}
