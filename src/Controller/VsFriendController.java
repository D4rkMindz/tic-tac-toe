package Controller;

import Service.LoginValidationService;
import Start.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by Björn on 17.03.2017.
 */
public class VsFriendController {
    @FXML
    private MainController mainController;

    public void setStart(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void handleLogin() {

    }
}
