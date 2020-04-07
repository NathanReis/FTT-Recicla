/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.presentation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import recicla.business.crud.CadastraUsuario;
import recicla.comuns.vos.Usuario;

/**
 * FXML Controller class
 *
 * @author italo
 */
public class TelaCadastroController implements Initializable {

    @FXML
    private Button btnCadastraAluno;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private TextField txtNome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CadastraUsuario(ActionEvent event) throws SQLException {
        Usuario user = new Usuario();
        user.setUsuario(txtLogin.getText());
        user.setSenha(txtSenha.getText());
        user.setNome(txtNome.getText());
        
        CadastraUsuario insere = new CadastraUsuario();
        boolean inserir = insere.InsereUsuario(user);
        
        if (inserir) {
            System.out.println("inseriu com sucesso");
        } else {
            System.out.println("Falha ao Inserir");
        }
        
    }
    
}
