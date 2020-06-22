package recicla.presentation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import static com.oracle.jrockit.jfr.ContentType.Class;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import recicla.business.acesso.Acesso;
import recicla.business.config.Config;
import recicla.business.httpRequests.httpRequest;
import recicla.comuns.vos.ItemLojaXUsuario;
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
    private final String USER_AGENT = "Mozilla/5.0";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnEntrar(MouseEvent event) throws Exception {
        Usuario user = new Usuario();
        user.setUsuario(txtLogin.getText());
        user.setSenha(txtSenha.getText());

        String chamadaWS;
        try {
            //chamadaWS = "http://localhost:8080/ReciclaWebServices/webresources/user/obtem-usuario/";
            chamadaWS = "user/obtem-usuario/";
            String json = httpRequest.sendGet(chamadaWS + user.getUsuario() + "/" + user.getSenha());
            Gson g = new Gson();
            System.out.print(json);
            Usuario u = new Usuario();

            Type usuarioType = new TypeToken<Usuario>() {
            }.getType();
            Type itensType = new TypeToken<List<ItemLojaXUsuario>>() {
            }.getType();

            u = g.fromJson(json, usuarioType);
            chamadaWS = "itens/obtem-itens-usuario/";
            String itensJson = httpRequest.sendGet(chamadaWS + u.getUsuarioId());
            System.out.print(itensJson);

            if (u != null) {
                //u.setItens(g.fromJson(json, itensType));
                //ItemLojaXUsuario itens = g.fromJson(json, ItemLojaXUsuario.class);
                ItemLojaXUsuario[] itens = g.fromJson(itensJson, ItemLojaXUsuario[].class);
                //listaitens.add(itens);
                u.setItens(itens);
                Config.getInstance().setLoggedUser(u);
                try {
                    //Parent root = FXMLLoader.load(getClass().getResource("TelaHomeAluno.fxml")); 
                    Parent root = FXMLLoader.load(getClass().getResource("TelaJogoQuiz.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login");
                alert.setContentText("Login inválido, tente novamente.");
                alert.showAndWait();
                System.out.println("Usuario invalido");
            }
        } catch (Exception erro) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login");
            alert.setContentText("Login inválido, tente novamente.");
            alert.showAndWait();
            System.out.println("Usuario invalido");
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
