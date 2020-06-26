/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.presentation;

import com.google.gson.Gson;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import recicla.comuns.vos.Usuario;
import javafx.scene.control.Alert;
import recicla.business.httpRequests.httpRequest;

/**
 * FXML Controller class
 *
 * @author italo
 */
public class TelaCadastroController implements Initializable {

    @FXML
    private Button btnCadastraAluno;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private TextField txtNome;
    
    String url = "user/adciona-usuario";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CadastraUsuario(ActionEvent event) throws SQLException, Exception {
       Usuario user = new Usuario();
       user.setUsuario(txtLogin.getText());
       user.setSenha(txtSenha.getText());
       user.setNome(txtNome.getText());
       
       Gson g = new Gson();
       
       String json = g.toJson(user);
       String retorno = httpRequest.sendPost(json, url);
       
       if(retorno.equalsIgnoreCase("invalid"))
       {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cadastro");
            alert.setContentText("Cadastro inválido, verifique se o tamanho está entre 1 e 15 caracteres.");
            alert.showAndWait();
       }
       else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cadastro");
            alert.setContentText("Cadastrado com sucesso.");
            alert.showAndWait();
        }
                     
    }
    
}
