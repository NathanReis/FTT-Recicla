package recicla.presentation;

import com.google.gson.Gson;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import recicla.business.config.Config;
import recicla.business.httpRequests.httpRequest;
import recicla.comuns.helperController.HelperController;
import recicla.comuns.vos.Rodada;

/**
 * FXML Controller class
 */
public class TelaExibeSalaController implements Initializable {

    @FXML
    private Label txt_titulo_sala;
    @FXML
    private Button btnAdicionaRodada;
    @FXML
    private TextField txtSalaId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.txtSalaId.setText(Integer.toString(Config.getInstance().getSalaAtualEditando()));
    }    

    @FXML
    private void btnAdicionaRodada(ActionEvent event) {
        try {
            Rodada rodada = new Rodada();
            rodada.setSalaId(Integer.parseInt(this.txtSalaId.getText()));
            
            String chamadaWS = "rodada/adciona-rodada/";
            Gson g = new Gson();
            
            int idInserido = Integer.parseInt(httpRequest.sendPost(g.toJson(rodada), chamadaWS));
            
            if(idInserido != 0) {
                Config.getInstance().setRodadaAtualEditando(idInserido);
                
                Parent root = FXMLLoader.load(getClass().getResource("TelaRodada.fxml"));

                HelperController.exibirTela(root);
            } else {
                System.out.println("Por algum motivo não foi inserido");
            }
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
