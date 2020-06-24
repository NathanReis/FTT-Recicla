package recicla.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class TelaHomeProfessorController implements Initializable {
    @FXML
    private Button btnCriaSala;
    @FXML
    private Button btnJogos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    



    
      /*private void btnCriaSala(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("TelaCadastroSala.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch(Exception e) {
        e.printStackTrace();
    }
    }*/

    @FXML
    private void telaCriaSala(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("TelaCadastroSala.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void CadastraQuiz(ContextMenuEvent event) throws IOException {
        
       
    }

    @FXML
    private void CadastraPergunta(MouseEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("TelaCadastroPergunta.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
    }
}