package recicla.comuns.helperController;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import recicla.comuns.vos.JogoRodada;

public class HelperController {
    public static void exibirTela(Parent parentNextScene) throws Exception {
        Scene nextScene = new Scene(parentNextScene);
        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.show();
    }
    
    
     public static String dicover_game(JogoRodada game) throws IOException {
        String tela = "";
//        Parent root;

        switch (game.getJogoId()) {

            case 1: {
                tela = "TelaAcerteAlvo.fxml";
                break;
            }
            case 2: {
                tela = "TelaJogoMemoria.fxml";
                break;
            }
            case 3: {
                tela = "TelaJogoQuiz.fxml";
                break;
            }

        }

        if (tela == "") {
            return null;
        } else {

            return tela;
        }

    }
}
