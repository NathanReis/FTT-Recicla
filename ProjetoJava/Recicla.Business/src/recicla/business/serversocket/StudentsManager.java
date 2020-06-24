/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.serversocket;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import recicla.comuns.helperController.HelperController;
import recicla.comuns.vos.JogoRodada;
import javafx.application.Application;



/**
 *
 * @author italo
 */
public class StudentsManager implements Runnable{
    
     private ConcurrentLinkedQueue<JogoRodada> games;  
     private Socket clientSocket;
     private final int id_quiz_game = 3;
     private final int id_memory_game = 2;
     private final int id_target_game = 1;
     private JogoRodada current_game;
     
     
    @Override
    public void run() {
        System.out.println("The student enter in the room");
        RoundMannager.getInstance().setIsRoomAvaliable(true);
    }
    
    private String dicover_game(JogoRodada game) throws IOException {
        String tela = "";
//        Parent root;

        switch (game.getJogoId()) {

            case 1: {
                tela = "TelaAcerteAlvo.fxml";
                break;
            }
            case 2: {
                tela = "TelaJogoMemoria.fxml";
                break;
            }
            case 3: {
                tela = "TelaJogoQuiz.fxml";
                break;
            }

        }

        if (tela == "") {
            return null;
        } else {
            
            return tela;
        }

    }

    private void set_game_status_true(JogoRodada game){
       //The Round manager will be responsible for turn the game status to true
       //Each game when they finished will turn they status to false.
       switch (game.getJogoId()) {

            case 1: {
                RoundMannager.getInstance().setTarget_game(true);
                break;
            }
            case 2: {
                RoundMannager.getInstance().setMemory_game(true);
                break;
            }
            case 3: {
                RoundMannager.getInstance().setQuiz_game(true);
                break;
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
