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
import java.util.ArrayList;
import java.util.List;
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
import recicla.business.config.Config;
import recicla.business.httpRequests.httpRequest;
import recicla.business.serversocket.RoundMannager;
import recicla.comuns.helperController.HelperController;
import recicla.comuns.vos.JogoRodada;
import recicla.comuns.vos.Rodada;

/**
 *
 * @author vitorlupinetti
 */
public class TelaEsperaRankingController implements Initializable {

    @FXML
    private Button btn_conectar;
    private String finalUrl = "rodada/obtem-rodada-por-salaId/";
    private List<JogoRodada> jogos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void Conecta_Ranking(MouseEvent event) {
        try {
            exibeRanking();
        } catch (Exception ex) {
            Logger.getLogger(TelaEsperaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void exibeRanking() throws IOException, Exception {

        Gson g = new Gson();
        int salaId = Config.getInstance().getSalaAtualEditando();
        String rodadaJson = httpRequest.sendGet(finalUrl + salaId);
        Rodada rodadaAtual = g.fromJson(rodadaJson, Rodada.class);
        Config.getInstance().setRodadaAtualEditando(rodadaAtual.getRodadaId());

        if (rodadaAtual.getStatusRodada() == 0) {
            Parent root = FXMLLoader.load(getClass().getResource("TelaRanking.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Jogo");
            alert.setContentText("O professor ainda não encerrou a rodada");
            alert.showAndWait();
        }

    }

}
