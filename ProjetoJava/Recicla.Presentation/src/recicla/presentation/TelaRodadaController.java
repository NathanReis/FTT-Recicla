package recicla.presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 */
public class TelaRodadaController implements Initializable {
    @FXML
    private Label txt_titulo_rodada;
    @FXML
    private Label txt_titulo_sala2;
    @FXML
    private Label txt_titulo_sala1;
    @FXML
    private Label txt_titulo_sala11;
    @FXML
    private Label txt_titulo_sala111;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnExcluiJogo(ActionEvent event) {
    }
}