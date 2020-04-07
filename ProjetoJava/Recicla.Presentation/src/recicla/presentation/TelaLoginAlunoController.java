package recicla.presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
                System.out.println("Usuario valido");
            } else {
                System.out.println("Usuario invalido");
            }
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }
}