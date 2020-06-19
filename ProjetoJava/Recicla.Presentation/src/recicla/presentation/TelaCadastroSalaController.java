/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.presentation;

import com.google.gson.Gson;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import recicla.business.httpRequests.httpRequest;
import recicla.comuns.vos.Sala;

/**
 * FXML Controller class
 *
 * @author italo
 */
public class TelaCadastroSalaController implements Initializable {

    @FXML
    private Button btn_cadastrar;
    @FXML
    private TextField txt_NomeSala;

    String url = "sala/adciona-sala";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CadastraSala(MouseEvent event) throws Exception {
        Sala sala = new Sala();
        sala.setDescricao(txt_NomeSala.getText());       
       

        Gson g = new Gson();
        String json = g.toJson(sala);
        String retorno = httpRequest.sendPost(json, url);

        if (retorno.equalsIgnoreCase("invalid")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cadastro");
            alert.setContentText("Cadastro inválido, verifique campos em branco.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cadastro");
            alert.setContentText("Cadastrado com sucesso.");
            alert.showAndWait();
        }
    }
    
}
