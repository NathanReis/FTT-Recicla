/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.serversocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author italo
 */
public class SalaServer extends Thread {
    
    private static ServerSocket server;
    private static int port = 11340;
    private boolean servidorAtivo;
    private List<StudentsManager> ConectedStudents = Collections.synchronizedList(new ArrayList<StudentsManager>());

    @Override
    public void run() {
        try {
            servidorAtivo = true;
            server = new ServerSocket(port);
            while (servidorAtivo) {
                System.out.println("Waiting for students enter in the room...");
                Socket socket = server.accept();
                Treat_students(socket);
                Thread.sleep(10);
            }
            if (!server.isClosed()) {
                server.close();
            }
        } catch (IOException | InterruptedException ex) {
            if (ex.getMessage().equals("socket closed")) {
                System.out.println("Conexão server encerrada...");
            } else {
                Logger.getLogger(SalaServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void Treat_students(Socket socket) {
        StudentsManager manager = new StudentsManager();
        manager.setClientSocket(socket);
        ConectedStudents.add(manager);
        Thread threadSocket = new Thread(manager);
        threadSocket.start();
    }

    public void Finish() throws IOException {
        ConectedStudents.forEach((student) -> {
            try {
                student.finish();
            } catch (IOException ex) {
                Logger.getLogger(SalaServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        servidorAtivo = false;
        server.close();
    }
    
     public void ListStudents() {
        for (StudentsManager cliente : ConectedStudents){
                cliente.show_yourself();
        }
    }
     
    public void Make_Room_Avaliable() {
        for (StudentsManager cliente : ConectedStudents){
                  Thread threadSocket = new Thread(cliente);
                  threadSocket.start();
        }
    }
      
      

    
}
