package recicla.presentation;

import com.google.gson.Gson;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import recicla.business.config.Config;
import recicla.business.httpRequests.httpRequest;
import recicla.comuns.helperController.HelperController;
import recicla.comuns.vos.JogoRodada;

/**
 * FXML Controller class
 */
public class TelaRodadaController implements Initializable {
    private ArrayList<String> jogosCadastrados = new ArrayList<String>();
    private final int APAGUE_A_LUZ_ID = 1;
    private final int JOGO_MEMORIA_ID = 2;
    private final int QUIZ_ID = 3;

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
            if(idInserido != 0) {
                if(jogoId == 3) {
                    // Pegar pergunta(s) selecionadas
                }
            } else {
                System.out.println("Por algum motivo não foi inserido");
            }
        } catch(Exception exception) {
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
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @FXML
    private void btnStart_Click(ActionEvent event) {
        try {
            String proximaTela = "";
            System.out.println("Rodada iniciada");
            
            switch(this.jogosCadastrados.get(0).toLowerCase()) {
                case "3":
                    proximaTela = "TelaJogoQuiz";
                    break;
                    
                case "1":
                    proximaTela = "TelaAcerteAlvo";
                    break;
                    
                case "2":
                    proximaTela = "TelaJogoMemoria";
                    break;
                    
                default:
                    throw new Exception("Nenhum jogo cadastrado");
            }
            
            Parent root = FXMLLoader.load(getClass().getResource(proximaTela + ".fxml"));

            HelperController.exibirTela(root);
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
