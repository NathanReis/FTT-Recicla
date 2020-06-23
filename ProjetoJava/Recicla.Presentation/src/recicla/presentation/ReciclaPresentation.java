package recicla.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import recicla.business.config.Config;
import recicla.comuns.enums.TipoRepositorio;

public class ReciclaPresentation extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("TelaAcerteAlvo.fxml"));

        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Config.getInstance().setTipoRepositorio(TipoRepositorio.MYSQL);

        launch(args);
    }
}