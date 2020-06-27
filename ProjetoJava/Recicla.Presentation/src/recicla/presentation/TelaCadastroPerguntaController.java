package recicla.presentation;

import com.google.gson.Gson;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import recicla.business.httpRequests.httpRequest;
import recicla.comuns.vos.PerguntaQuiz;

/**
 * FXML Controller class
 */
public class TelaCadastroPerguntaController implements Initializable {
    @FXML
    private TextField txt_alternativa_1;
    @FXML
    private TextField txt_descricao_pergunta;
    @FXML
    private TextField txt_alternativa_2;
    @FXML
    private TextField txt_alternativa_3;
    @FXML
    private TextField txt_alternativa_correta;
    @FXML
    private Button btn_salvar_pergunta;
    String urlfinal = "pergunta-quiz/adcionar-pergunta";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_salva_pergunta(ActionEvent event)throws SQLException, Exception {
       
        PerguntaQuiz pergunta = new PerguntaQuiz();
        pergunta.setPergunta(txt_descricao_pergunta.getText());
        pergunta.setAlternativa1(txt_alternativa_1.getText());
        pergunta.setAlternativa2(txt_alternativa_2.getText());
        pergunta.setRespostaCorreta(txt_alternativa_correta.getText());
        pergunta.setJogoId(1);
       
        Gson g = new Gson();
        String perguntaJson = g.toJson(pergunta);
        System.out.print(perguntaJson);
        String response = httpRequest.sendPost(perguntaJson, urlfinal);
        
        if(response.equalsIgnoreCase("invalid"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cadastro");
            alert.setContentText("Cadastro Inválido. Campos em  branco ou ultrapassagem de limite de caracteres");
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