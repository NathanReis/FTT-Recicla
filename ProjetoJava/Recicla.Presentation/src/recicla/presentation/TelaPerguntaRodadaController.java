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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import recicla.business.httpRequests.httpRequest;
import recicla.comuns.vos.PerguntaQuiz;
import recicla.comuns.vos.Usuario;

/**
 *
 * @author vitorlupinetti
 */
public class TelaPerguntaRodadaController implements Initializable {

    
    @FXML
    private ListView listQuiz;
    private String finalUrl = "pergunta-quiz/listar-perguntas";
    private ObservableList<PerguntaQuiz> perguntas;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            perguntas = FXCollections.observableArrayList();
            String response = httpRequest.sendGet(finalUrl);
            Gson g = new Gson();
            Type perguntaType = new TypeToken<ArrayList<PerguntaQuiz>>() {
            }.getType();
            perguntas = g.fromJson(response, perguntaType);
            listQuiz = new ListView<PerguntaQuiz>(perguntas);
        } catch (Exception ex) {
            Logger.getLogger(TelaPerguntaRodadaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}