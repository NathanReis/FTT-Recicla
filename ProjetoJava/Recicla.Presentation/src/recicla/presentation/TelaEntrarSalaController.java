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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import recicla.business.config.Config;
import recicla.business.httpRequests.httpRequest;
import recicla.comuns.vos.Sala;
import recicla.comuns.vos.Usuario;

/**
 *
 * @author vitorlupinetti
 */
public class TelaEntrarSalaController implements Initializable {

    @FXML
    private TextField txtCodigoSala;
    @FXML
    private Button btnEntrarSala;
    private String finalUrl = "sala/obtem-sala-por-chave/";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void entrarSala() throws Exception {
        String chave = txtCodigoSala.getText();

        String salaJson = httpRequest.sendGet(finalUrl + chave);

        Usuario usuario = Config.getInstance().getLoggedUser();
        Gson g = new Gson();
        Sala salaRetornada = g.fromJson(salaJson, Sala.class);
        if (salaJson.equals("null")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("Chave inválida, verifique os campos.");
            alert.showAndWait();
        } else {
            if (usuario.getTipoUsuario().equalsIgnoreCase("A")) {
                Config.getInstance().setSalaAtualEditando(salaRetornada.getSalaId());
                enviaParaLobby();
            } else {
                Config.getInstance().setSalaAtualEditando(salaRetornada.getSalaId());
                enviaParaEdicaoRodada();
            }
        }

    }

    private void enviaParaLobby() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("TelaEspera.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviaParaEdicaoRodada() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("TelaExibeSala.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
