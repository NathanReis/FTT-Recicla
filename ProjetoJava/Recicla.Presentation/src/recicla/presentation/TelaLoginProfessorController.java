/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.presentation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import recicla.business.config.Config;
import recicla.business.httpRequests.httpRequest;
import recicla.comuns.vos.ItemLojaXUsuario;
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

        String chamadaWS;
        try {
            if (user.getUsuario().equals("professor")) {
                chamadaWS = "user/obtem-usuario/";
                String json = httpRequest.sendGet(chamadaWS + user.getUsuario() + "/" + user.getSenha());
                Gson g = new Gson();
                System.out.print(json);
                Usuario u = new Usuario();

                Type usuarioType = new TypeToken<Usuario>() {
                }.getType();
                Type itensType = new TypeToken<List<ItemLojaXUsuario>>() {
                }.getType();

                u = g.fromJson(json, usuarioType);

                if (u != null) {
                    
                    Config.getInstance().setLoggedUser(u);
                    Stage stage = (Stage) txt_login.getScene().getWindow();
                    stage.close();
                    Parent root = FXMLLoader.load(getClass().getResource("TelaHomeProfessor.fxml"));
                    Scene scene = new Scene(root);
                    stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login");
                    alert.setContentText("Login inválido, tente novamente.");
                    alert.showAndWait();

                }
            }else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login");
                    alert.setContentText("Este usuário não pertende a de um professor");
                    alert.showAndWait();
            
            }

        } catch (Exception ex) {

            System.out.println(ex.getMessage());

        }

    
        
    
  

    }
    
}
