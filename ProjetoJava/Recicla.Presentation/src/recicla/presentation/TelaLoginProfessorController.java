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
import recicla.business.acesso.Acesso;
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

    }
    
}
