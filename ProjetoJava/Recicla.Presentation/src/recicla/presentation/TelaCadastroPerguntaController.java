package recicla.presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_salva_pergunta(ActionEvent event) {
    }
}