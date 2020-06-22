package recicla.presentation;

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
import javafx.scene.layout.Pane;
import recicla.comuns.helperController.HelperController;

/**
 * FXML Controller class
 */
public class TelaRodadaController implements Initializable {
    private ArrayList<String> jogosCadastrados = new ArrayList<String>();

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnExcluiJogo(ActionEvent event) {
        System.out.println("Removido: " + this.jogosCadastrados.get(this.jogosCadastrados.size() - 1));
        this.jogosCadastrados.remove(this.jogosCadastrados.size() - 1);
        atualizarQtdExibida();
    }

    @FXML
    private void btnEscolherPregunta_Click(ActionEvent event) {
        this.adicionarJogo("Pergunta Quiz");
    }

    @FXML
    private void btnAdicionarJogoMemoria_Click(ActionEvent event) {
        this.adicionarJogo("Jogo da memória");
    }

    @FXML
    private void btnAdicionarApagueALuz_Click(ActionEvent event) {
        this.adicionarJogo("Apague a luz");
    }
    
    private void adicionarJogo(String jogo) {
        System.out.println("Adicionado: " + jogo);
        this.jogosCadastrados.add(jogo);
        atualizarQtdExibida();
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
                case "pergunta quiz":
                    proximaTela = "TelaJogoQuiz";
                    break;
                    
                case "apague a luz":
                    proximaTela = "TelaAcerteAlvo";
                    break;
                    
                case "jogo da memória":
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
