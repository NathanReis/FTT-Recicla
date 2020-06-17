package recicla.presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import recicla.business.crud.Loja;

/**
 * FXML Controller class
 */
public class TelaLojaController implements Initializable {
    @FXML
    private Label txtDinheiro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCompraTempo(ActionEvent event) throws Exception {
        boolean isValid = Loja.comprarItem(1);
    }

    @FXML
    private void btnCompraResposta(ActionEvent event) throws Exception {
        boolean isValid = Loja.comprarItem(2);
    }

    @FXML
    private void btnCompraPontos(ActionEvent event) throws Exception {
        boolean isValid = Loja.comprarItem(3);
    }
    
    private void emiteAlerta(boolean isValid){
        if(isValid)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Compra");
            alert.setContentText("Compra Realizada!");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Compra");
            alert.setContentText("Ops, algo deu errado com a compra, tente novamente mais tarde!");
            alert.showAndWait();
        }
    }
}