package Controller;

import Start.MainController;
import javafx.fxml.FXML;

public class ProxyController {
    @FXML
    private MainController mainController;

    @FXML
    public void setStart(MainController mainController) {

        this.mainController = mainController;
    }

    @FXML
    private void returnToLogin() {
        mainController.initLoginGui();
    }

    @FXML
    private void handleSettings() {

    }
}
