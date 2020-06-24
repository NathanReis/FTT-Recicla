package recicla.presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TelaHomeAlunoController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private Button btnLoja;
    @FXML
    private Button button2;
    @FXML
    private Button btnAcessarSala;
    
    @FXML
    private void btnLojaClick(ActionEvent event) {
         try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("TelaLoja.fxml")); 
            Parent root = (Parent) fxml.load();
            Stage stage = new Stage();
            stage.setTitle("Loja");
            stage.setScene(new Scene(root));           
            stage.show();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ExibeHomeAluno(ActionEvent event) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("TelaLoginAluno.fxml")); 
            Parent root = (Parent) fxml.load();
            Stage stage = new Stage();
            stage.setTitle("Home Aluno");
            stage.setScene(new Scene(root));           
            stage.show();
        } catch(Exception e) {
            System.out.println("Falha ao carregar a janela");
        }
    }
    
    @FXML
    private void btnAcessarSalaClicked(){
         try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("TelaEntrarSala.fxml")); 
            Parent root = (Parent) fxml.load();
            Stage stage = new Stage();
            stage.setTitle("Entrar Sala");
            stage.setScene(new Scene(root));           
            stage.show();
        } catch(Exception e) {
            System.out.println("Falha ao carregar a janela");
        }
    }
}