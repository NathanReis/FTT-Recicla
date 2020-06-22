package recicla.comuns.helperController;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelperController {
    public static void exibirTela(Parent parentNextScene) throws Exception {
        Scene nextScene = new Scene(parentNextScene);
        Stage nextStage = new Stage();
        nextStage.setScene(nextScene);
        nextStage.show();
    }
}
