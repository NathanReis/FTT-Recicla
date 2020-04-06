/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author italo
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
        
        Acesso acesso = new Acesso();
        boolean usuariovalido = acesso.validaUsuario(user);        
        if(usuariovalido){
        //acessa home
        }else{
        //mensagem de erro
        }
        
    }
    
}
