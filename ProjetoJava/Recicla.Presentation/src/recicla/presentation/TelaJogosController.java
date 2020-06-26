package recicla.presentation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import recicla.business.httpRequests.httpRequest;
import recicla.comuns.vos.ItemLojaXUsuario;
import recicla.comuns.vos.PerguntaQuiz;
import recicla.business.config.Config;
import recicla.business.serversocket.RoundMannager;
import recicla.comuns.helperController.HelperController;
import recicla.comuns.vos.JogoRodada;
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
    private int multiplicador = 1;
    private int tempoTimer;
    @FXML
    private ImageView itemQuiz;
    @FXML
    private ImageView itemTempo;
    @FXML
    private Label item2x;
    
    private Timer timer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtPergunta.setWrapText(true);
        perguntas = new ArrayList<>();
        try {
            String response = httpRequest.sendGet(finalUrl);
            Gson g = new Gson();
            Type perguntaType = new TypeToken<ArrayList<PerguntaQuiz>>() {
            }.getType();
            perguntas = g.fromJson(response, perguntaType);
            Time(30);
            verificaItensUsuario();
            trocaPergunta();

        } catch (Exception ex) {
            Logger.getLogger(TelaJogosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //Time

    private void Time(int tempo) {

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int interval = tempo;

            public void run() {
                if (interval > 0) {
                    Platform.runLater(() -> txtTempo.setText(String.valueOf(interval)));
                    tempoTimer = interval;
                    interval--;
                } else {
                  Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Fim do jogo do Quiz");
                            JogoRodada game = RoundMannager.getInstance().remove_game();
                            if (game != null) {
                                try {
                                    String tela = HelperController.dicover_game(game);
                                    Parent root = FXMLLoader.load(getClass().getResource(tela));
                                    HelperController.exibirTela(root);
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }

                            } else {
                                System.out.println("Fim da Rodada");
                            }
                        }
// ...
                    });
                    timer.cancel();
                }
            }
        }, 1000, 1000);
        
    }
    
    private void cancelTimer(){
        timer.cancel();
        Time(tempoTimer + 30);
    }

    @FXML
    private void btnAlt1Clicked() throws Exception {
        verificaResposta(txtAlternativa1.getText());
    }

    @FXML
    private void btnAlt2Clicked() throws Exception {
        verificaResposta(txtAlternativa2.getText());
    }

    @FXML
    private void btnAlt3Clicked() throws Exception {
        verificaResposta(txtAlternativa3.getText());
    }

    @FXML
    private void itemTempoClicked() throws Exception {
        cancelTimer();
        consomeItem(1);
        verificaItensUsuario();
    }

    @FXML
    private void itemQuizClicked() throws Exception {
        consomeItem(2);
        verificaResposta(this.perguntaAtual.getRespostaCorreta());
        verificaItensUsuario();
    }
    
    @FXML
    private void item2xClicked() throws Exception{
        consomeItem(3);
        multiplicador = multiplicador * 2;
        verificaItensUsuario();
    }

    private void verificaResposta(String resposta) throws IOException, Exception {
        if (perguntaAtual.getRespostaCorreta().equalsIgnoreCase(resposta)) {
            alertaCorreto();
            calculaPontos(true);

            if (perguntas.size() > numQuestao + 1) {
                numQuestao++;
                trocaPergunta();
            } else {
                JogoRodada game = RoundMannager.getInstance().remove_game();
               if(game!= null){
                String tela = HelperController.dicover_game(game);
                Parent root = FXMLLoader.load(getClass().getResource(tela));
                HelperController.exibirTela(root);
               
               }else{               
                   System.out.println("Fim da Rodada");
               }
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fim");
                alert.setContentText("Fim do quiz.");
                alert.showAndWait();
            }
        } else {
            alertaIncorreto();
            calculaPontos(false);
        }
    }

    private void alertaCorreto() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Correto");
        alert.setContentText("Alternativa correta.");
        alert.showAndWait();

    }

    private void alertaIncorreto() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errado");
        alert.setContentText("Alternativa incorreta.");
        alert.showAndWait();

    }

    private void trocaPergunta() {
        perguntaAtual = perguntas.get(numQuestao);
        txtPergunta.setText(perguntaAtual.getPergunta());
        txtAlternativa1.setText(perguntaAtual.getAlternativa1());
        txtAlternativa2.setText(perguntaAtual.getAlternativa2());
        txtAlternativa3.setText(perguntaAtual.getRespostaCorreta());
    }

    private void calculaPontos(boolean acertou) {
        if (acertou) {
            pontos += 10 * multiplicador;
        } else {
            pontos -= 5;
            if (pontos < 0) {
                pontos = 0;
            }
        }
        txtPontuacao.setText(Integer.toString(pontos));

    }

    private void verificaItensUsuario() {
        itemQuiz.setVisible(false);
        itemTempo.setVisible(false);
        item2x.setVisible(false);
        
        ItemLojaXUsuario[] itens = Config.getInstance().getLoggedUser().getItens();
        for (ItemLojaXUsuario item : itens) {
            if (item.getItemLojaId() == 1 && item.getQuantidade() >= 1) {
                itemTempo.setVisible(true);
            }
            //apenas no quiz
            if (item.getItemLojaId() == 2 && item.getQuantidade() >= 1) {
                itemQuiz.setVisible(true);
            }
            if (item.getItemLojaId() == 3 && item.getQuantidade() >= 1) {
                item2x.setVisible(true);
            }

        }
    }

    private void consomeItem(int idItem) throws Exception {
        ItemLojaXUsuario[] itens = Config.getInstance().getLoggedUser().getItens();
        Gson g = new Gson();
        for (ItemLojaXUsuario item : itens) {
            if (item.getItemLojaId() == idItem) {
                int qtd = item.getQuantidade();
                item.setQuantidade(qtd - 1);
                String chamadaWs = "itens/consome-item-usuario";
                httpRequest.sendPut(g.toJson(item), chamadaWs);
                break;
            }
        }
        Config.getInstance().getLoggedUser().setItens(itens);
    }
}
