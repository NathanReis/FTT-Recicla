/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.presentation;

import com.google.gson.Gson;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import recicla.business.config.Config;
import recicla.business.crud.CadastraSala;
import recicla.business.httpRequests.httpRequest;
import recicla.comuns.helperController.HelperController;
import recicla.comuns.vos.Sala;

/**
 * FXML Controller class
 *
 * @author italo
 */
public class TelaCadastroSalaController implements Initializable {

    @FXML
    private Button btn_cadastrar;
    @FXML
    private TextField txt_NomeSala;
    String url = "sala/adciona-sala";


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CadastraSala(MouseEvent event) throws Exception {
        Sala sala = new Sala();
        sala.setDescricao(txt_NomeSala.getText());       
       
        /*CadastraSala crud = new CadastraSala();
        boolean isValid = crud.InsereSala(sala);
        System.out.print(isValid);*/
        Gson g = new Gson();
        String json = g.toJson(sala);
        String retorno = httpRequest.sendPost(json, url);
        System.out.print(retorno);
        if (retorno.equals("invalid")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cadastro");
            alert.setContentText("Cadastro inválido, verifique campos em branco.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cadastro");
            alert.setContentText("Cadastrado com sucesso.");
            alert.showAndWait();
            
            url = "sala/obtem-sala-por-chave/";
            Sala salaRetornada = g.fromJson(retorno, Sala.class);
            retorno = httpRequest.sendGet(url + salaRetornada.getChaveAcesso());
            // Configura no singleton qual sala será editada
            Config.getInstance().setSalaAtualEditando(g.fromJson(retorno, Sala.class).getSalaId());
            
            // Abre a tela para editar a sala cadastrada
            Parent root = FXMLLoader.load(getClass().getResource("TelaExibeSala.fxml"));
            HelperController.exibirTela(root);
        }
    }
    
}
