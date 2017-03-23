package Controller;

import Start.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sun.awt.image.ImageWatched;

public class RegisterController {
    @FXML
    private MainController mainController;

    public void setStart(MainController mainController) {

        this.mainController = mainController;
    }

    @FXML
    private void validateRegistration() {

        mainController.initRegisterGui();
    }

    @FXML
    private void goBack() {

        mainController.initLoginGui();
    }
}
