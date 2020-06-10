package recicla.presentation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import recicla.business.httpRequests.httpRequest;
import recicla.comuns.vos.PerguntaQuiz;
import recicla.comuns.vos.Usuario;

/**
 * FXML Controller class
 */
public class TelaJogosController implements Initializable {
    @FXML
    private Label txtDinheiro;
    @FXML
    private Label txtTempo;
    @FXML
    private Label txtTempo1;
    @FXML
    private Label txtPergunta;
    @FXML
    private Label txtAlternativa1;
    @FXML
    private Label txtAlternativa2;
    @FXML
    private Label txtAlternativa3;
    @FXML
    private Label txtPontuacao;
    String finalUrl = "pergunta-quiz/listar-perguntas";
    PerguntaQuiz perguntaAtual;
    Alert alert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<PerguntaQuiz> perguntas = new ArrayList<>();
        try {
            String response = httpRequest.sendGet(finalUrl);
            Gson g = new Gson();
            Type perguntaType = new TypeToken<ArrayList<PerguntaQuiz>>() {}.getType();
            perguntas = g.fromJson(response, perguntaType);
            System.out.print(perguntas.get(0));
            
            
            PerguntaQuiz pergunta = new PerguntaQuiz();
            pergunta = perguntas.get(1);
            perguntaAtual = pergunta;
            
            txtPergunta.setText(pergunta.getPergunta());
            txtAlternativa1.setText(pergunta.getAlternativa1());
            txtAlternativa2.setText(pergunta.getAlternativa2());
            txtAlternativa3.setText(pergunta.getRespostaCorreta());
            
        } catch (Exception ex) {
            Logger.getLogger(TelaJogosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void btnAlt1Clicked() {

        if(perguntaAtual.getRespostaCorreta().equalsIgnoreCase(txtAlternativa1.getText())){
               alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Correto");
               alert.setContentText("Alternativa correta.");
               alert.showAndWait();
               return;
        }
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errado");
        alert.setContentText("Alternativa incorreta.");
        alert.showAndWait();
    }
    
     @FXML
    private void btnAlt2Clicked() {
         if(perguntaAtual.getRespostaCorreta().equalsIgnoreCase(txtAlternativa2.getText())){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Correto");
                alert.setContentText("Alternativa correta.");
                alert.showAndWait();
                return;
         }
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errado");
        alert.setContentText("Alternativa incorreta.");
        alert.showAndWait();
    }
     @FXML
    private void btnAlt3Clicked() {
         if(perguntaAtual.getRespostaCorreta().equalsIgnoreCase(txtAlternativa3.getText())){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Correto");
                alert.setContentText("Alternativa correta.");
                alert.showAndWait();
                return;
         }
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errado");
        alert.setContentText("Alternativa incorreta.");
        alert.showAndWait();
    }
    
     
}