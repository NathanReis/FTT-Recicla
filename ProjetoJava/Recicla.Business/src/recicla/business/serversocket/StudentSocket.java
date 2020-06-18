/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.serversocket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author italo
 */
public class StudentSocket extends Thread{
    
    @Override
    public void run() {
        InetAddress host;
        try {
            host = InetAddress.getByName("25.13.118.102");
            Socket socket = null;
            socket = new Socket(host.getHostName(), 11340);
//            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//            oos.writeObject("Olá mundo!");
//            oos.close();
//            socket.close();
        } catch (UnknownHostException ex) {
            Logger.getLogger(StudentSocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
