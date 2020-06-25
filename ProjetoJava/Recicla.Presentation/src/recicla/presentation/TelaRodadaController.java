package recicla.presentation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import recicla.business.config.Config;
import recicla.business.httpRequests.httpRequest;
import recicla.business.serversocket.RoundMannager;
import recicla.business.serversocket.SalaServer;
import recicla.business.serversocket.StudentSocket;
import recicla.comuns.helperController.HelperController;
import recicla.comuns.vos.JogoRodada;
import recicla.comuns.vos.Rodada;
import recicla.comuns.vos.Sala;

/**
 * FXML Controller class
 */
public class TelaRodadaController implements Initializable {

    private ArrayList<String> jogosCadastrados = new ArrayList<String>();
    private final int APAGUE_A_LUZ_ID = 1;
    private final int JOGO_MEMORIA_ID = 2;
    private final int QUIZ_ID = 3;
    private List<JogoRodada> jogos;

    @FXML
    private Button btnAdicionarApagueALuz;
    @FXML
    private Button btnAdicionarJogoMemoria;
    @FXML
    private Button btnEscolherPergunta;
    @FXML
    private Button btnExcluirRodada;
    @FXML
    private Button btnStart;
    @FXML
    private Label lblJogosCadastrados;
    @FXML
    private Label lblQtdJogosCadasreados;
    @FXML
    private Label lblTituloRodada;
    @FXML
    private Pane pnlJogosCadastrados;
    @FXML
    private TextField txtRodadaId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.txtRodadaId.setText(Integer.toString(Config.getInstance().getRodadaAtualEditando()));
    }

    @FXML
    private void btnExcluiJogo(ActionEvent event) {
        System.out.println("Removido: " + this.jogosCadastrados.get(this.jogosCadastrados.size() - 1));
        this.jogosCadastrados.remove(this.jogosCadastrados.size() - 1);
        atualizarQtdExibida();
    }

    @FXML
    private void btnEscolherPregunta_Click(ActionEvent event) {
        this.adicionarJogo(this.QUIZ_ID);
    }

    @FXML
    private void btnAdicionarJogoMemoria_Click(ActionEvent event) {
        this.adicionarJogo(this.JOGO_MEMORIA_ID);
    }

    @FXML
    private void btnAdicionarApagueALuz_Click(ActionEvent event) {
        this.adicionarJogo(this.APAGUE_A_LUZ_ID);
    }

    private void adicionarJogo(int jogoId) {
        try {
            System.out.println("Adicionado: " + jogoId);
            this.jogosCadastrados.add(Integer.toString(jogoId));
            atualizarQtdExibida();

            JogoRodada jogoRodada = new JogoRodada();
            jogoRodada.setRodadaId(Integer.parseInt(this.txtRodadaId.getText()));
            jogoRodada.setJogoId(jogoId);

            String chamadaWS = "rodada/adciona-jogo-rodada/";
            Gson g = new Gson();

            int idInserido = Integer.parseInt(httpRequest.sendPost(g.toJson(jogoRodada), chamadaWS));
            if (idInserido != 0) {
                if (jogoId == 3) {
                    // Pegar pergunta(s) selecionadas
                }
            } else {
                System.out.println("Por algum motivo não foi inserido");
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void atualizarQtdExibida() {
        this.lblQtdJogosCadasreados.setText(Integer.toString(this.jogosCadastrados.size()));
    }

    @FXML
    private void btnExcluirRodada_Click(ActionEvent event) {
        try {
            System.out.println("Rodada excluída");
            Parent root = FXMLLoader.load(getClass().getResource("TelaExibeSala.fxml"));

            HelperController.exibirTela(root);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @FXML
    private void btnStart_Click(ActionEvent event) {
        try {
//              
            String URL = "rodada/obtem-rodada-por-salaId/";

            Gson g = new Gson();
            int chaveSala = Config.getInstance().getSalaAtualEditando();
            String rodadaJson = httpRequest.sendGet(URL + chaveSala);
            Rodada rodadaAtual = g.fromJson(rodadaJson, Rodada.class);
            rodadaAtual.setStatusRodada(1);
            URL = "rodada/inicia-rodada";
            httpRequest.sendPut(g.toJson(rodadaAtual), URL);



            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Rodada Iniciada");
            alert.setContentText("A rodada foi iniciada com sucesso");
            alert.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("TelaHomeProfessor.fxml"));
            HelperController.exibirTela(root);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void add_jogo_singleton() {
        for (JogoRodada game : jogos) {
            RoundMannager.getInstance().add_game(game);
        }

    }
}
