/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import recicla.business.serversocket.RoundMannager;
import recicla.business.serversocket.SalaServer;
import recicla.business.serversocket.StudentSocket;
import recicla.business.serversocket.StudentsManager;
import recicla.comuns.helperController.HelperController;
import recicla.comuns.vos.JogoRodada;

/**
 * FXML Controller class
 *
 * @author italo
 */
public class TelaEsperaController implements Initializable {

    @FXML
    private Button btn_conectar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
//            //Teste multiplaye
//        SalaServer sala = new SalaServer();
//        sala.start();     
//        
//        //Pega Jogos 
//        JogoRodada jogo_memoria = new JogoRodada();
//        JogoRodada jogo_acerte = new JogoRodada();
//        
//     
//        jogo_memoria.setJogoId(2);
//        jogo_acerte.setJogoId(1);        
//      
//         RoundMannager.getInstance().add_game(jogo_memoria);
//         RoundMannager.getInstance().add_game(jogo_acerte);
//        
//        //Jogador conecta na sala
//        StudentSocket aluno = new StudentSocket();
//        aluno.start();
//        
      
    }    

    @FXML
    private void Conecta_Sala(MouseEvent event) {
          try {
            Primeira_Tela();
        } catch (Exception ex) {
            Logger.getLogger(TelaEsperaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Primeira_Tela() throws IOException, Exception{

        if (RoundMannager.getInstance().isIsRoomAvaliable()) {

            //cal the first game of round
            JogoRodada game = RoundMannager.getInstance().remove_game();
            String tela = HelperController.dicover_game(game);
//                Parent root = FXMLLoader.load(getClass().getResource(tela));
//                HelperController.exibirTela(root);

            Parent root = FXMLLoader.load(getClass().getResource(tela));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        }else{
        
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Jogo");
            alert.setContentText("O professor ainda não começou a rodada");
            alert.showAndWait();
            System.out.println("Rodada iniciada");
        }

    }
    

        
        
        
        
    
}
