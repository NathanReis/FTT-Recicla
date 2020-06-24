/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.presentation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import recicla.business.crud.CadastraSala;
import recicla.business.serversocket.RoundMannager;
import recicla.business.serversocket.SalaServer;
import recicla.business.serversocket.StudentSocket;
import recicla.comuns.vos.JogoRodada;
import recicla.comuns.vos.Usuario;

/**
 * FXML Controller class
 *
 * @author italo
 */
public class TelaLoginProfessorController implements Initializable {

    @FXML
    private TextField txt_login;
    @FXML
    private PasswordField txt_senha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnEntrar(MouseEvent event) throws SQLException, IOException {
        Usuario user = new Usuario();
        user.setUsuario(txt_login.getText());
        user.setSenha(txt_senha.getText());
        
        //Is miss the validation by API

        Parent root = FXMLLoader.load(getClass().getResource("TelaHomeProfessor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

//        //Teste multiplaye
//        SalaServer sala = new SalaServer();
//        sala.start();     
//        
//        //Pega Jogos
//        JogoRodada jogo_quiz = new JogoRodada();
//        JogoRodada jogo_memoria = new JogoRodada();
//        JogoRodada jogo_acerte = new JogoRodada();
//        
//        jogo_quiz.setJogoId(3);
//        jogo_memoria.setJogoId(2);
//        jogo_acerte.setJogoId(1);
//        
//         RoundMannager.getInstance().add_game(jogo_quiz);
//         RoundMannager.getInstance().add_game(jogo_memoria);
//         RoundMannager.getInstance().add_game(jogo_acerte);
//        
//        //Jogador conecta na sala
//        StudentSocket aluno = new StudentSocket();
//        aluno.start();
//        
//          //Torna a sala disponível
//        RoundMannager.getInstance().setIsRoomAvaliable(true);
       
  

    }
    
}
