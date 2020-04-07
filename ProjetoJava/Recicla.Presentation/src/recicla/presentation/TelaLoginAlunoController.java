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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import recicla.business.acesso.Acesso;
import recicla.comuns.vos.Usuario;

/**
 * FXML Controller class
 */
public class TelaLoginAlunoController implements Initializable {
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Button btnValidaAcesso;
    @FXML
    private Hyperlink txtNaoCadastro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnEntrar(MouseEvent event) {
        Usuario user = new Usuario();
        user.setUsuario(txtLogin.getText()); 
        user.setSenha(txtSenha.getText());
        
        try {
            Acesso acesso = new Acesso();
            boolean usuariovalido = acesso.validaUsuario(user);

            if(usuariovalido) {
                
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("TelaHomeAluno.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }               
                
                
            } else {
                System.out.println("Usuario invalido");
            }
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    @FXML
    private void ExibeCadastroAluno(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("TelaCadastro.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }           
                
    }
}