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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author vitorlupinetti
 */
public class TelaEntrarSalaController implements Initializable {

    @FXML
    private TextField txtCodigoSala;
    @FXML
    private Button btnEntrarSala;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void entrarSala(){
        String chave = txtCodigoSala.getText();
        System.out.print("chave inserida: " + chave);
    }
}