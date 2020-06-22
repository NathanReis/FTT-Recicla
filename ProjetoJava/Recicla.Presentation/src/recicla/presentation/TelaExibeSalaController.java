package recicla.presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import recicla.comuns.helperController.HelperController;

/**
 * FXML Controller class
 */
public class TelaExibeSalaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAdicionaRodada(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("TelaRodada.fxml"));

            HelperController.exibirTela(root);
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
