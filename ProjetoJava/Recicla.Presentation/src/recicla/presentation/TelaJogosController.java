package recicla.presentation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import recicla.business.httpRequests.httpRequest;
import recicla.comuns.vos.ItemLojaXUsuario;
import recicla.comuns.vos.PerguntaQuiz;
import recicla.business.config.Config;
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
    private String finalUrl = "pergunta-quiz/listar-perguntas";
    private PerguntaQuiz perguntaAtual;
    private Alert alert;
    private int numQuestao = 0;
    private List<PerguntaQuiz> perguntas;
    private int pontos = 0;
    @FXML
    private ImageView itemQuiz; 
    @FXML
    private ImageView itemTempo;
    @FXML
    private Label item2x;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        perguntas = new ArrayList<>();
        try {
            String response = httpRequest.sendGet(finalUrl);
            Gson g = new Gson();
            Type perguntaType = new TypeToken<ArrayList<PerguntaQuiz>>() {}.getType();
            perguntas = g.fromJson(response, perguntaType);
            Time();
            itemQuiz.setVisible(false);
            itemTempo.setVisible(false);
            item2x.setVisible(false);

            verificaItensUsuario();
            trocaPergunta();
            
        } catch (Exception ex) {
            Logger.getLogger(TelaJogosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     //Time
    private void Time(){

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int interval = 30;

            public void run() {
                if (interval > 0) {
                    Platform.runLater(() -> txtTempo.setText(String.valueOf(interval)));   
                    interval--;
                } else {
                    timer.cancel();
                }
            }
        }, 1000, 1000);
    
    }
    
    
    @FXML
    private void btnAlt1Clicked() {
        verificaResposta(txtAlternativa1.getText());    
    }
    
     @FXML
    private void btnAlt2Clicked() {
         verificaResposta(txtAlternativa2.getText());    
    }
     @FXML
    private void btnAlt3Clicked() {
        verificaResposta(txtAlternativa3.getText());        
    }
    
    @FXML
    private void itemTempoClicked(){
        int tempo = Integer.getInteger(this.txtTempo.getText());
        tempo += 30;
        this.txtTempo.setText(Integer.toString(tempo));
        verificaItensUsuario();
    }
    
    private void verificaResposta(String resposta){
        if(perguntaAtual.getRespostaCorreta().equalsIgnoreCase(resposta)){
            alertaCorreto();
            calculaPontos(true);

            if(perguntas.size() > numQuestao + 1){
                numQuestao++;
                trocaPergunta();
            }
            else{
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fim");
                alert.setContentText("Fim do quiz.");
                alert.showAndWait();
            }
        }
        else{
            alertaIncorreto();
            calculaPontos(false);
        }
    }
    
    private void alertaCorreto(){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Correto");
        alert.setContentText("Alternativa correta.");
        alert.showAndWait();
        
    }
    
     private void alertaIncorreto(){
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errado");
        alert.setContentText("Alternativa incorreta.");
        alert.showAndWait();
        
    }
    
    private void trocaPergunta(){
        perguntaAtual = perguntas.get(numQuestao);
        txtPergunta.setText(perguntaAtual.getPergunta());
        txtAlternativa1.setText(perguntaAtual.getAlternativa1());
        txtAlternativa2.setText(perguntaAtual.getAlternativa2());
        txtAlternativa3.setText(perguntaAtual.getRespostaCorreta());
    }
     
    private void calculaPontos(boolean acertou){
        if(acertou){
            pontos+= 10;
        }
        else{
            pontos-= 5;
            if(pontos < 0)
                pontos = 0;
        }
        txtPontuacao.setText(Integer.toString(pontos));

    }
    
    private void verificaItensUsuario(){
        ItemLojaXUsuario[] itens = Config.getInstance().getLoggedUser().getItens();
       for (ItemLojaXUsuario item : itens) {
            if(item.getItemLojaId() == 1 && item.getQuantidade() > 1){
                itemTempo.setVisible(true);
            }
            //apenas no quiz
            if(item.getItemLojaId() == 2 && item.getQuantidade() > 1){
                itemQuiz.setVisible(true);
            }
            if(item.getItemLojaId() == 3 && item.getQuantidade() > 1){
                item2x.setVisible(true);
            }
                        
        }
    }
}